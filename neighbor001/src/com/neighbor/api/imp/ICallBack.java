package com.neighbor.api.imp;

public interface ICallBack<T> {
	
	void onResult(T result);
	void onError(Throwable err);

}
