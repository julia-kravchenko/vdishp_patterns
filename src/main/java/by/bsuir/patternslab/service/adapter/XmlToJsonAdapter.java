package by.bsuir.patternslab.service.adapter;

import by.bsuir.patternslab.utils.Constants;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;

public class XmlToJsonAdapter implements Converter {
    JsonWork jsonWork;

    public XmlToJsonAdapter() {
        jsonWork = new JsonWork();
    }

    @Override
    public void convertXmlToJson() {
        writeFromXmlToJson();
        jsonWork.printPublicationsInJsonFormat();
    }

    public void writeFromXmlToJson() {
        try {
            File file = new File(Constants.FILEDOM);
            InputStream inputStream = new FileInputStream(file);
            StringBuilder builder = new StringBuilder();
            int ptr = 0;
            while ((ptr = inputStream.read()) != -1) {
                builder.append((char) ptr);
                //  System.out.println(ptr);
            }

            String xml = builder.toString();
            JSONObject jsonObj = XML.toJSONObject(xml);

            FileWriter fileWriter = new FileWriter(Constants.PUBLICATIONS_JSON);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            for (int i = 0; i < jsonObj.toString().split(",").length; i++) {
                bufferedWriter.write(jsonObj.toString().split(",")[i] + ',');
                bufferedWriter.write("\n");
            }

            System.out.println(jsonObj);

            bufferedWriter.close();
        }


            /*
            String xmlString  = "<?xml version=\"1.0\"?><ASF_Service_ResponseVO id=\"1\"><service type=\"String\">OnboardingV2</service><operation type=\"String\">start_onboarding_session</operation><requested_version type=\"String\">1.0</requested_version><actual_version type=\"String\">1.0</actual_version><server_info type=\"String\">onboardingv2serv:start_onboarding_session&CalThreadId=85&TopLevelTxnStartTime=13b40fe91c4&Host=L-BLR-00438534&pid=3564</server_info><result type=\"Onboarding::StartOnboardingSessionResponse\" id=\"2\"><onboarding_id type=\"String\">137</onboarding_id><success type=\"bool\">true</success></result></ASF_Service_ResponseVO>";

            JSONObject jsonObj = XML.toJSONObject(xmlString);
            System.out.println(jsonObj.toString());
            */ catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + Constants.PUBLICATIONS_JSON + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
