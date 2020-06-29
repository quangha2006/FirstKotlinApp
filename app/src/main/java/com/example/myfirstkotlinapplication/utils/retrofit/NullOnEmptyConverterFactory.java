package com.example.myfirstkotlinapplication.utils.retrofit;

import com.example.myfirstkotlinapplication.data.entity.response.BaseResponse;
import com.example.myfirstkotlinapplication.utils.AppUtils;

import java.io.EOFException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NullOnEmptyConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);

        return (body) -> {
            if (String.class.equals(type)) {
                String text = new String(body.bytes(), Charset.defaultCharset());
                return AppUtils.decode(text);
            }

            try {
                return delegate.convert(body);
            } catch (EOFException e) {
                BaseResponse response = new BaseResponse();
                response.setMessage(e.getMessage());
                response.setStatus(0);
                return response;
            }
        };
    }

}

