package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entity.Virus;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.repository.VirusRepository;
import org.softuni.residentevil.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public VirusServiceModel spread(VirusServiceModel virusServiceModel) {
        if (!this.validatorUtil.isValid(virusServiceModel)) {
            //TODO: throw exception or return null.
        }
        Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
        Virus spreadVirus = this.virusRepository.saveAndFlush(virus);

        return this.modelMapper.map(spreadVirus, VirusServiceModel.class);
    }

    @Override
    public List<VirusServiceModel> findAll() {
        List<VirusServiceModel> viruses = this.virusRepository.findAll()
                .stream()
                .map(virus -> this.modelMapper.map(virus, VirusServiceModel.class))
                .collect(Collectors.toList());
        return viruses;
    }

    @Override
    public VirusServiceModel findById(String id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.virusRepository.deleteById(id);
    }
}
