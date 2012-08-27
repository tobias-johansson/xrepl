/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.xtext.scripting;

import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.xbase.scoping.featurecalls.StaticImplicitMethodsFeatureForTypeProvider;

import com.google.common.collect.Multimap;

/**
 * @author tobiasjohansson - Initial contribution and API
 */
public class ASExtensionClassNameProvider extends StaticImplicitMethodsFeatureForTypeProvider.ExtensionClassNameProvider {

	@Override
	protected Multimap<Class<?>, Class<?>> simpleComputeExtensionClasses() {
		Multimap<Class<?>, Class<?>> classes = super.simpleComputeExtensionClasses();
		
		classes.put(List.class, MyListExtensions.class);
		
		return classes;
	}
	
	@Override
	protected Collection<String> computeLiteralClassNames() {
		Collection<String> classNames = super.computeLiteralClassNames();
		
		classNames.add(MyStaticExtensions.class.getName());
		
		return classNames;
	}
	
}

