package by.bsuir.patternslab.service.abstractfactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public abstract class DOMParser implements XMLParser {
    public Document document = null;
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;


    public DOMParser(String resource) throws ParserConfigurationException, IOException, SAXException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(new File(resource));
    }

  /*  @Override
    public ArrayList<Publication> parseProducts() {
        if (document == null) {
            System.out.println("document == null");
            return null;
        }

        ArrayList<Publication> appliances = getProducts(Constants.BOOK);
        ArrayList<Publication> largeAppliances = getProducts(Constants.MAGAZINE);
        appliances.addAll(largeAppliances);
        return appliances;
    }*/

//    private ArrayList<Publication> getProducts(String element) {
//        ArrayList<Publication> products = new ArrayList<Publication>();
//        NodeList elements = document.getElementsByTagName(element);
//
//        for (int i = 0; i < elements.getLength(); i++) {
//            NamedNodeMap attributes = elements.item(i).getAttributes();
//
//            String name = attributes.getNamedItem("name").getNodeValue();
//            String company = attributes.getNamedItem("company").getNodeValue();
//            String model = attributes.getNamedItem("model").getNodeValue();
//            String price = attributes.getNamedItem("price").getNodeValue();
//
//            switch (element) {
//                case Constants.BOOK: { /*это для считывания из fileDOM.xml тега <large> */
//                    String height = attributes.getNamedItem("height").getNodeValue();
//                    String length = attributes.getNamedItem("length").getNodeValue();
//                    String width = attributes.getNamedItem("width").getNodeValue();
//                    String weight = attributes.getNamedItem("weight").getNodeValue();
//
//                  /*  Appliances largeAppl = new LargeAppliances(name, company, model, Double.parseDouble(price),
//                            "LARGE appliances", Double.parseDouble(height), Double.parseDouble(length),
//                            Double.parseDouble(width), Double.parseDouble(weight));*/
//                    // shop.addAppliance(largeAppl);
//                  //  products.add(largeAppl);
//                }
//                break;
//
//              /*  case Constants.SMALL: { /*это для считывания из fileDOM.xml тега <small> */
//                  /*  String isBuilt = attributes.getNamedItem("isBuiltIn").getNodeValue();
//
//                    Appliances smallAppl = new SmallAppliances(name, company, model, Double.parseDouble(price),
//                            "SMALL appliances", Boolean.parseBoolean(isBuilt));
//                    //  shop.addAppliance(smallAppl);
//                    products.add(smallAppl);
//                }*/
//             //   break;
//            }
//        }
//        return products;
//    }

//    public void writeDataXML(SmallAppliances smallAppliance) {
//        Node root = document.getDocumentElement();
//
//   //     System.out.println("root = " + root.getNodeName() + " tostr" + root.toString());
//        Element prod = document.createElement(Constants.SMALL);
//        prod.setAttribute("name", smallAppliance.getName());
//        prod.setAttribute("company", smallAppliance.getCompany());
//        prod.setAttribute("model", smallAppliance.getModel());
//        prod.setAttribute("price", smallAppliance.getPrice().toString());
//        prod.setAttribute("isBuiltIn", smallAppliance.getBuiltIn().toString());
//        root.appendChild(prod);
//        // Сохраняем Document в XML-файл
//        if (document != null)
//            writeDocument();
//    }
//
//    private void writeDocument()
//            throws TransformerFactoryConfigurationError {
//        Transformer trf = null;
//        DOMSource src = null;
//        FileOutputStream file = null;
//        try {
//            trf = TransformerFactory.newInstance()
//                    .newTransformer();
//            src = new DOMSource(document);
//            file = new FileOutputStream(Constants.FILEDOM);
//
//            StreamResult result = new StreamResult(file);
//            trf.transform(src, result);
//        } catch (TransformerException | FileNotFoundException e) {
//            e.printStackTrace(System.out);
//        }
//    }

}
