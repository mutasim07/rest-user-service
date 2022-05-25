package com.perseus.urs.userrestservice.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Transformer<R, P> {

	R toEntity(P p);

	P toModel(R r);

	default List<R> toEntities(List<P> p) {
		return p != null ? p.stream().map(this::toEntity).collect(Collectors.toList()) : new ArrayList<>();
	}

	default List<P> toModels(List<R> r) {
		return r != null ? r.stream().map(this::toModel).collect(Collectors.toList()) : new ArrayList<>();
	}
}
