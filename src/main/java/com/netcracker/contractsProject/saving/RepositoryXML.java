package com.netcracker.contractsProject.saving;

import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * A class that implements saving and restoring a repository in xml format
 */
public class RepositoryXML implements Save, Restore {
    /**
     * The path to the file where the repository is saved in xml format
     */
    private static final String PATH_TO_XML_FILE = "repository.xml";
    private static final Logger LOGGER = Logger.getRootLogger();
    private final JAXBContext jc;

    public RepositoryXML() throws JAXBException {
        this.jc = JAXBContext.newInstance(Repository.class);
    }

    /**
     * Restores repository from xml format
     *
     * @return restored repository
     */
    @Override
    public IRepository restore() {
        IRepository repository = null;
        try {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            repository = (IRepository) unmarshaller.unmarshal(new File(PATH_TO_XML_FILE));
        } catch (JAXBException e) {
            LOGGER.error("Error restoring repository from xml file", e);
        }
        return repository;
    }

    /**
     * Saves the repository in xml format
     *
     * @param repository repository to save
     */
    @Override
    public void save(IRepository repository) {
        try {
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(repository, new File(PATH_TO_XML_FILE));
        } catch (JAXBException e) {
            LOGGER.error("Error while saving repository in xml file", e);
        }
    }
}
