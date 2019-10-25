package org.softuni.springessentialsintro.service;

import org.modelmapper.ModelMapper;
import org.softuni.springessentialsintro.domain.entity.Document;
import org.softuni.springessentialsintro.domain.model.binding.DocumentScheduleBindingModel;
import org.softuni.springessentialsintro.domain.model.service.DocumentServiceModel;
import org.softuni.springessentialsintro.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean schedule(DocumentScheduleBindingModel documentScheduleBindingModel) {
        if (!validateDocument(documentScheduleBindingModel)) {
            return false;
        }
        Document document = this.modelMapper.map(documentScheduleBindingModel, Document.class);
        this.documentRepository.saveAndFlush(document);
        return true;
    }

    @Override
    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);
        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        List<DocumentServiceModel> allDocuments = this.documentRepository.findAll()
                .stream()
                .map(doc -> this.modelMapper.map(doc, DocumentServiceModel.class))
                .collect(Collectors.toList());
        ;

        return allDocuments;
    }

    @Override
    public boolean print(String id) {
        try {
            this.documentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean validateDocument(DocumentScheduleBindingModel documentScheduleBindingModel) {

        String title = documentScheduleBindingModel.getTitle();
        String content = documentScheduleBindingModel.getContent();

        return title != null && content != null;
    }
}
