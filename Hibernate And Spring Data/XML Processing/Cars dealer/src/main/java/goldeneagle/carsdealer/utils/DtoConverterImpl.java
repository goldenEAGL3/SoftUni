package goldeneagle.carsdealer.utils;

import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DtoConverterImpl implements DtoConverter {

    private final ModelMapper mapper;

    @Autowired
    public DtoConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

    @Override
    public <S, D> List<D> convert(Iterable<S> source, Class<D> destinationClass) {
        List<D> resultList = new ArrayList<>();
        for (S s : source) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }
        return resultList;
    }

    @Override
    public <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }
}
