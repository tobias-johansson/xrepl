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
import org.xtext.scripting.interpreter.ScriptingInterpreter;
import org.xtext.scripting.scoping.ScriptingScopeProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ScriptingRuntimeModule extends org.xtext.scripting.AbstractScriptingRuntimeModule {

	@Override
	public Class<? extends IExpressionInterpreter> bindIExpressionInterpreter() {
		return ScriptingInterpreter.class;
	}
	
	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return ScriptingScopeProvider.class;
	}
}
