/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.tools;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.DetailData;
import com.BTS.converter.entities.HistoryFile;
import com.BTS.converter.entities.Parameter;
import com.BTS.converter.entities.CorporateType;
import com.BTS.converter.entities.Extension;
import com.BTS.converter.entities.Temp;
import com.BTS.converter.entities.TrExtension;
import com.BTS.converter.repositories.ClientPartnerRepository;
import com.BTS.converter.repositories.DetailDataRepository;
import com.BTS.converter.repositories.ExtensionRepository;
import com.BTS.converter.repositories.HistoryFileRepository;
import com.BTS.converter.repositories.ParameterRepository;
import com.BTS.converter.repositories.tempRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.BTS.converter.services.DetailDataService;
import com.BTS.converter.services.ClientPartnerService;
import com.BTS.converter.services.ExtensionService;
import com.BTS.converter.services.ParameterService;
import com.BTS.converter.services.TrExtensionService;
import com.BTS.converter.services.tempService;
import com.BTS.converter.utils.CSVUtils;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

/**
 *
 * @author Reza
 */
@Service
@Component
@Transactional
public class Methods {

    public static Logger logger = LogManager.getLogger(Methods.class.getName());
    ///Detail data
    @Autowired
    DetailDataRepository dataRepository;

    @Autowired
    DetailDataService dataService;
///end ofDetail data

//    extension
    @Autowired
    ExtensionService extensionService;

    @Autowired
    ExtensionRepository extensionRepository;
//end of extension

//    client partner
    @Autowired
    ClientPartnerService clientService;

    @Autowired
    ClientPartnerRepository clientPartnerRepository;
//    end of client partner

//    temp
    @Autowired
    tempRepository tempRepository;

    @Autowired
    tempService tempService;
//    end of temp

//    trExtension
    @Autowired
    TrExtensionService trExtensionService;
//    end of trExtension

//    history
    @Autowired
    HistoryFileRepository historyRepository;
//    end of history

//    parameter
    @Autowired
    ParameterRepository paramRepository;

    @Autowired
    ParameterService paramService;
//            end of parameter

    public String converting(String path, String filename, String clientId, String delimiter_data, String delimiter_end, String OrFilenName) throws IOException {
        String result = "";
        File file = new File(path + "/" + filename);
        FileWriter writer = new FileWriter(path + "/" + filename);
        /////////////////////////////////
        DetailData details = new DetailData();
        ClientPartner client = (ClientPartner) clientService.getById(clientId);

        for (Temp detail : tempRepository.getByName(OrFilenName)) {
            List<String> temp = new ArrayList<>();
            String a = "";
            String b = "";
            String c = "";
            String d = "";
            String e = "";
            String f = "";
            String g = "";
            String h = "";
            String i = "";
            String j = "";
            logger.info("Try -> " + detail.getField1() + delimiter_data + detail.getField2() + delimiter_data
                    + detail.getField3() + delimiter_data
                    + detail.getField4() + delimiter_data
                    + detail.getField4() + delimiter_data
                    + detail.getField5() + delimiter_data
                    + detail.getField6() + delimiter_data
                    + detail.getField7() + delimiter_data
                    + detail.getField8() + delimiter_data
                    + detail.getField9() + delimiter_data
                    + detail.getField10() + delimiter_end);
            a = detail.getField1();
            b = detail.getField2();
            c = detail.getField3();
            d = detail.getField4();
            e = detail.getField5();
            f = detail.getField6();
            g = detail.getField7();
            h = detail.getField8();
            i = detail.getField9();
            j = detail.getField10();

            //////////////////////////////////////////////
            temp.add(a);
            temp.add(b);
            temp.add(c);
            temp.add(d);
            temp.add(e);
            temp.add(f);
            temp.add(g);
            temp.add(h);
            temp.add(i);
            temp.add(j);
            //////////////////////////////////////////////
            CSVUtils.writeLine(writer, temp, delimiter_data);
        }
        result = "convert ok";
        writer.flush();
        writer.close();
        return result;
    }

    public void renameExtension(String path) {
        File oldfile = new File(path);
        int dotPos = path.lastIndexOf(".");
        String strExtension = path.substring(dotPos + 1);
        String strFilename = path.substring(0, dotPos);
        String strNewFileName = strFilename + "." + "NACK";
        System.out.println("filename -> " + strFilename);
        System.out.println("extension -> " + strExtension);
        System.out.println("newfilename -> " + strNewFileName);
        File newfile = new File(strNewFileName);
        oldfile.renameTo(newfile);
        boolean Rename = oldfile.renameTo(newfile);
        if (!Rename) {
            System.out.println("FileExtension hasn't been changed successfully.");
        } else {
            System.out.println("FileExtension has been changed successfully.");
        }
    }

    public Integer countRowFile(String path) {
        File file = new File(path);
        int result = 0;
//        count row file
        try {
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                int LineNumber = 0;
                while (lnr.readLine() != null) {
                    LineNumber++;
                }
                System.out.println("Total number of lines : " + LineNumber);
                result = LineNumber;
                lnr.close();
            } else {
                System.out.println("file do not exsist");
            }
        } catch (Exception e) {
        }
        return result;
    }

    static String[] split(String st, char delimiter) {
        char ch[] = st.toCharArray();
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == delimiter) {
                count++;
            }
        }

        String str[] = new String[count + 1];
        int k = 0;
        str[k] = "";
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != delimiter) {
                str[k] = str[k] + ch[i];
            } else {
                k++;
                str[k] = "";
            }
        }
        return str;
    }

    public void readCsvUsingLoad(String path, String delimiter, String clientId) {
        File file = new File(path);
        String filename = file.getName();
        String line = null;
        char delim = delimiter.charAt(0);
        ////
        Temp temp = new Temp();
        ClientPartner cli = new ClientPartner();
        cli.setId(new BigDecimal(clientId));
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
//                System.out.println("line -> " + line);
                String[] line_split = split(line, delim);
                if (line_split.length == 1) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2("");
                        temp.setField3("");
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2("");
                        temp.setField3("");
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                } else if (line_split.length == 2) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3("");
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3("");
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                } else if (line_split.length == 3) {
                    if (tempRepository.loopIdTemp() == null) {

                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {

                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4("");
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {

                            temp = new Temp();
                        } else {

                            temp = new Temp();
                        }
                    }
                } else if (line_split.length == 4) {
                    if (tempRepository.loopIdTemp() == null) {

                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5("");
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }
                } else if (line_split.length == 5) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6("");
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                } else if (line_split.length == 6) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7("");
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }
                } else if (line_split.length == 7) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8("");
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                } else if (line_split.length == 8) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9("");
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {

                            temp = new Temp();
                        } else {

                            temp = new Temp();
                        }
                    }

                } else if (line_split.length == 9) {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9(line_split[8]);
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9(line_split[8]);
                        temp.setField10("");
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));
                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                } else {
                    if (tempRepository.loopIdTemp() == null) {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9(line_split[8]);
                        temp.setField10(line_split[9]);
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(1));

                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    } else {
                        temp.setField1(line_split[0]);
                        temp.setField2(line_split[1]);
                        temp.setField3(line_split[2]);
                        temp.setField4(line_split[3]);
                        temp.setField5(line_split[4]);
                        temp.setField6(line_split[5]);
                        temp.setField7(line_split[6]);
                        temp.setField8(line_split[7]);
                        temp.setField9(line_split[8]);
                        temp.setField10(line_split[9]);
                        temp.setFileName(filename);
                        temp.setClient(cli);
                        temp.setId(new BigDecimal(tempRepository.loopIdTemp()));

                        if (tempService.save(temp)) {
                            temp = new Temp();
                        } else {
                            temp = new Temp();
                        }
                    }

                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println("File tidak ditemukan ");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("File tidak ditemukan ");
        }
    }

    public File[] getFileList(String dirPath, String ext) {
        File dir = new File(dirPath);
        List<String> fileExt = new ArrayList<>();
        File[] fileList;
        fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(ext);
            }
        });
        return fileList;
    }

    public void insert_into(String paths, String delimiter, String ext, String clientId) {
        File[] fileList = getFileList(paths, ext);

        for (File file : fileList) {
            readCsvUsingLoad(file.getPath(), delimiter, clientId);
            renameExtension(file.getPath());
        }
    }

    public String saveClient(String clientId, String name, String incoming, String outgoing, String parameter, String parameter2, String type) {
        String result = "";
        ClientPartner clients = new ClientPartner();
        Parameter params = new Parameter();
        CorporateType types = new CorporateType();

        if (clientPartnerRepository.loopIdClient() == null) {
            clients.setId(new BigDecimal(1));
            clients.setClientId(clientId);
            clients.setName(name);
            clients.setIncomingPath(incoming);
            clients.setOutgoingPath(outgoing);
            params.setId(new BigDecimal(parameter));
            clients.setParameter(params);

            clients.setParameter2(parameter2);
            types.setId(type);
            clients.setType(types);

            if (clientService.save(clients)) {
                System.out.println("success");
                result = "success";
            } else {
                System.out.println("yeee gagal");

            }
        } else {
            clients.setId(new BigDecimal(clientPartnerRepository.loopIdClient()));
            clients.setClientId(clientId);
            clients.setName(name);
            clients.setIncomingPath(incoming);
            clients.setOutgoingPath(outgoing);
            params.setId(new BigDecimal(parameter));
            clients.setParameter(params);

            clients.setParameter2(parameter2);
            types.setId(type);
            clients.setType(types);

            if (clientService.save(clients)) {
                System.out.println("success");
                result = "success";
            } else {
                System.out.println("yeee gagal");

            }
        }

        return result;
    }

    public DetailData saveDataDetail(String name, BigDecimal clientId) {
        DetailData data = new DetailData();
        ClientPartner clients = new ClientPartner();

        for (Temp temp : tempRepository.getByName(name)) {
            data.setField1(temp.getField1());
            data.setField2(temp.getField2());
            data.setField3(temp.getField3());
            data.setField4(temp.getField4());
            data.setField5(temp.getField5());
            data.setField6(temp.getField6());
            data.setField7(temp.getField7());
            data.setField8(temp.getField8());
            data.setField9(temp.getField9());
            data.setField10(temp.getField10());
            data.setFileName(name);
            clients.setId(clientId);
            data.setClient(clients);
            if (dataRepository.loopIdDetail() == null) {
                data.setId(new BigDecimal(1));
            } else {
                data.setId(new BigDecimal(dataRepository.loopIdDetail()));
            }
            dataService.save(data);
        }

        return data;
    }

    public Parameter saveParam(String symbol) {
        Parameter param = new Parameter();
        logger.info("Id" + paramRepository.loopIdParameter());
        if (paramRepository.loopIdParameter() == null || paramRepository.loopIdParameter() == 0) {
            logger.info("Next Id" + paramRepository.loopIdParameter());
            param.setId(new BigDecimal(1));
            param.setSymbol(symbol);
            if (paramService.save(param)) {
                logger.info("saved");
            } else {
                logger.info("unsaved");
            }
        } else {
            param.setId(new BigDecimal(paramRepository.loopIdParameter()));
            param.setSymbol(symbol);
            if (paramService.save(param)) {
                logger.info("saved");
            } else {
                logger.info("unsaved");
            }
        }
        return param;

    }
//
//    public Extension saveExtension(String extension) {
//        Extension exten = new Extension();
//
//        exten.setExtension(extension);
//        return exten;
//    }

    public TrExtension saveTransactionExtension(BigDecimal clientId, int extensionId) {
        TrExtension exten = new TrExtension();
        ClientPartner client = new ClientPartner();
        Extension extension = new Extension();
        if (extensionRepository.loopIdExtension() == null) {
            exten.setId(new BigDecimal(1));
            client.setId(clientId);
            exten.setClientId(client);
            extension.setId(new BigDecimal(extensionId));
            exten.setExtension(extension);
            if (trExtensionService.save(exten)) {
                logger.info("saved");
            } else {
                logger.info("unsaved");
            }
        } else {
            exten.setId(new BigDecimal(extensionRepository.loopIdExtension()));
            client.setId(clientId);
            exten.setClientId(client);
            extension.setId(new BigDecimal(extensionId));
            exten.setExtension(extension);
            if (trExtensionService.save(exten)) {
                logger.info("saved");
            } else {
                logger.info("unsaved");
            }
        }

        return exten;
    }

    public CorporateType saveType(String id, String name) {
        CorporateType type = new CorporateType();
        type.setId(id);
        type.setName(name);
        return type;

    }

    public HistoryFile saveHistory(String oldpath, String newpath, String oldFilename, String newfilename, String client, Date date) {
        HistoryFile history = new HistoryFile();
        ClientPartner clients = new ClientPartner();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        Date date1 = new Date();
        if (historyRepository.loopIdHistory() == null) {
            history.setId(new BigDecimal(1));
        } else {
            history.setId(new BigDecimal(historyRepository.loopIdHistory()));
        }

        history.setOldPath(oldpath);
        history.setNewPath(newpath);
        history.setOldFilename(oldFilename);
        history.setNewFilename(newfilename);
        clients.setId(new BigDecimal(client));
        history.setClient(clients);
        history.setDate(date);
        return history;
    }

    public String id_for_type(String str) {
        String first = str.substring(0, 1);
        String mid = middle(str);
        String last = str.substring(str.length() - 1);
        String temp = first + "" + mid + "" + last;
        System.out.println(temp.toUpperCase());
        return temp.toUpperCase();
    }

    public String id_for_client(String str) {
        String mid = middle(str);
        String last = str.substring(str.length() - 1);
        String temp = mid + "" + last;
        System.out.println(temp.toUpperCase());
        return temp.toUpperCase();
    }

    public static String middle(String str) {
        int position;
        int length;

        if (str.length() % 2 == 0) {
            position = str.length() / 2 - 1;
            length = 1;
        } else {
            position = str.length() / 2;
            length = 1;
        }
        return str.substring(position, position + length);
    }

}
