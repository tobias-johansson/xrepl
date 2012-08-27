/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.xtext.scripting;

import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.interpreter.IExpressionInterpreter;
import org.eclipse.xtext.xbase.scoping.featurecalls.StaticImplicitMethodsFeatureForTypeProvider;
import org.xtext.scripting.interpreter.ScriptingInterpreter;
import org.xtext.scripting.scoping.ScriptingImportedNamespaceScopeProvider;
import org.xtext.scripting.scoping.ScriptingScopeProvider;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ScriptingRuntimeModule extends org.xtext.scripting.AbstractScriptingRuntimeModule {

	@Override
	public void configure(Binder binder) {
		super.configure(binder);

		binder.bind(StaticImplicitMethodsFeatureForTypeProvider.ExtensionClassNameProvider.class).to(
				ASExtensionClassNameProvider.class);

	}

	@Override
	public Class<? extends IExpressionInterpreter> bindIExpressionInterpreter() {
		return ScriptingInterpreter.class;
	}

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return ScriptingScopeProvider.class;
	}

	@Override
	public void configureIScopeProviderDelegate(com.google.inject.Binder binder) {
		binder.bind(org.eclipse.xtext.scoping.IScopeProvider.class)
				.annotatedWith(
						Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
				.to(ScriptingImportedNamespaceScopeProvider.class);
	}
}
