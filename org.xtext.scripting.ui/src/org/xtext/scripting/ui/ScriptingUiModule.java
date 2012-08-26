/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.xtext.scripting.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.xrepl.Evaluator;
import org.xrepl.XreplResourceSetProvider;
import org.xrepl.ui.WorkspaceAwareEvaluator;
import org.xrepl.ui.XreplWorkspaceResourceSetProvider;
import org.xrepl.ui.console.XreplConsole;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
public class ScriptingUiModule extends org.xtext.scripting.ui.AbstractScriptingUiModule {
	public ScriptingUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void configure(Binder binder) {
		binder.bind(String.class).annotatedWith(Names.named(XreplConsole.CONSOLE_TITLE)).toInstance(getConsoleTitle());
		binder.bind(ResourceSet.class).toProvider(XreplResourceSetProvider.class);
		binder.bind(Evaluator.class).to(WorkspaceAwareEvaluator.class);
		binder.bind(XreplResourceSetProvider.class).to(XreplWorkspaceResourceSetProvider.class);
		super.configure(binder);
	}
	
	protected String getConsoleTitle() {
		return "Xbase";
	}
}
