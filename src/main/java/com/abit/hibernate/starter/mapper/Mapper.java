package com.abit.hibernate.starter.mapper;

public interface Mapper<F, T> {
    T mapFrom(F obj);
}
