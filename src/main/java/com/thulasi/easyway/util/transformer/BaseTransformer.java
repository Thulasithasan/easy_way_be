package com.thulasi.easyway.util.transformer;

public interface BaseTransformer<T, I> {

	I transform(T type);

}
