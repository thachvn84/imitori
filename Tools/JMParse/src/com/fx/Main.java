package com.fx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilderfactory.setIgnoringComments(true);
        documentbuilderfactory.setNamespaceAware(true);
        documentbuilderfactory.setXIncludeAware(true);


        try {
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.parse(new FileInputStream("JMdict_e.xml"));

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            Element element = document.getDocumentElement();
            String tagName = element.getTagName();
            System.out.println(tagName);

            NodeList nl = element.getChildNodes();
            System.out.println("nl count: " + nl.getLength());

            /*
                ELEMENT
                    entry {
                        ent_seq,                //Unique ID for each entry
                        k_ele[] {
                            keb,
                            ke_inf[],
                            ke_pri[]
                        },
                        r_ele[] {
                            reb,
                            re_nokanji?,
                            re_restr[],
                            re_inf[],
                            re_pri[]
                        },
                        sense[] {
                            stagk[],
                            stagr[],
                            pos[],
                            xref[],
                            ant[],
                            field[],
                            misc[],
                            s_inf[],
                            lsource[],  //"EN"
                            dial[],
                            gloss[]
                        }
                    }

             */
            if (nl != null) {
                int length = nl.getLength();
                for (int i = 0; i < length; i++) {
                    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nl.item(i);
                        if (el.getNodeName().contains("entry")) {
                            System.out.println("----- Entry ");
                            Element entry_ele = el;
                            NodeList et_nl = el.getChildNodes();
                            if (et_nl != null) {
                                int et_nl_len = et_nl.getLength();
                                for (int r0 = 0; r0 < et_nl_len; r0++) {
                                    if (et_nl.item(r0).getNodeType() == Node.ELEMENT_NODE) {
                                        Element et_nl_el = (Element) et_nl.item(r0);
                                        if (et_nl_el.getNodeName().contains("ent_seq")) {
                                            String ent_seq = et_nl_el.getTextContent();
                                        }
                                    }
                                }
                            }
                            /*
                            for (int j = 0; j < el.getElementsByTagName("reb").getLength(); j++) {
                                String word = el.getElementsByTagName("reb").item(j).getTextContent();
                                System.out.println(word);
                            }*/
                        }
                    }
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
