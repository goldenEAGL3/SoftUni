package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entity.Capital;
import org.softuni.residentevil.domain.model.service.CapitalServiceModel;
import org.softuni.residentevil.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalServiceModel> findAll() {
        List<CapitalServiceModel> allOrderByName = this.capitalRepository.findAllOrderByName().stream()
                .map(capital -> this.modelMapper.map(capital, CapitalServiceModel.class))
                .collect(Collectors.toList());
        return allOrderByName;
    }

    @Override
    public CapitalServiceModel findById(String id) {
        Capital capital = this.capitalRepository.findById(id).orElse(null);
        return this.modelMapper.map(capital, CapitalServiceModel.class);
    }
}
