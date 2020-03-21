package xmlprocessing.goldeneagle.utils.interfaces;

import java.util.List;
import java.util.Set;

public interface DtoConverter {

    <S, D> D convert(S source, Class<D> destinationClass);

    <S, D> List<D> convert(Iterable<S> source, Class<D> destinationClass);

    <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass);
}
