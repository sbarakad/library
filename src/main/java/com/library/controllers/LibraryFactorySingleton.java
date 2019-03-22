package com.library.controllers;

public class LibraryFactorySingleton {
	private static LibraryFactorySingleton singletonLibraryFactory;
	private ILibraryFactory factory;
	public static LibraryFactorySingleton instance() {
		if (singletonLibraryFactory == null) {
			singletonLibraryFactory = new LibraryFactorySingleton();
		}
		return singletonLibraryFactory;
	}
	
	public ILibraryFactory getFactory() {
		return factory;
	}
	
	public void build(ILibraryFactory factory) {
		this.factory = factory;
	}
	
	
}
