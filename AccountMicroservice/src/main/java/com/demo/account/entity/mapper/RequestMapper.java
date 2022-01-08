package com.demo.account.entity.mapper;

public interface RequestMapper<R, E> {
    E mapFrom(R request);
}
