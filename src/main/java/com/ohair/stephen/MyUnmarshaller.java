package com.ohair.stephen;

import com.ohair.stephen.jaxb.HardwareMonitorType;
import com.ohair.stephen.jaxb.ObjectFactory;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Specific unmarshaller for Afterburner.
 *
 * @author Stephen O'Hair
 */
public class MyUnmarshaller {

    private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;

    public MyUnmarshaller() {

        try {
            jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            unmarshaller = jaxbContext.createUnmarshaller();

        } catch (JAXBException e) {
            throw new RuntimeException("unable to create marshaller");
        }
    }

    @SuppressWarnings("unchecked")
    public HardwareMonitorType unmarshal(InputStream is) {
        JAXBElement<HardwareMonitorType> unmarshalledObject;
        try {
            unmarshalledObject = (JAXBElement<HardwareMonitorType>) unmarshaller
                    .unmarshal(is);
        } catch (JAXBException e) {
            throw new RuntimeException("unable to unmarshall");
        }
        return unmarshalledObject.getValue();
    }
}
