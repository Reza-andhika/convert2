/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.watcherConfig;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.watcherConfig.Coba;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import java.nio.file.WatchEvent;
import java.nio.file.attribute.BasicFileAttributes;
import javax.annotation.PostConstruct;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hp
 */
//@Component
//@ComponentScan("com.BTS.converter")
public class WatcherFile {

    @Autowired
    private ClientPartnerRepository partnerRepository;

    @Autowired
    private ClientPartnerService partnerService;

    
    private WatchService watcher;
    private java.lang.String path;
    
    private Map<WatchKey, Path> keys;
    private String yoi = "";

    public WatcherFile() {
    }

   
    
    @Autowired
    public WatcherFile(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();

        walkAndRegisterDirectories(dir);
    }

    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE);
        keys.put(key, dir);

    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    
    
    public void walkAndRegisterDirectories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Process all events for keys queued to the watcher
     *
     * @throws java.io.FileNotFoundException
     */

    public void processEvents() {
        for (;;) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();
                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                // print out event
                System.out.format("%s\n", child);
                File file = new File(child.toString());
                String path = file.getParent();
                System.out.println("pathnya -> " + file.getParent());
//                Coba coba = new Coba();
//                coba.setPath_fwtch(path);
                


                // if directory is created, and watching recursively, then register it and its sub-directories
                if (kind == ENTRY_CREATE) {
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
   
}
