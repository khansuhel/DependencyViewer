package com.dm.magnitude.exceptions;

public class CircularDependencyException extends RuntimeException {
	public CircularDependencyException(String message){
		super(message);
	}
}
