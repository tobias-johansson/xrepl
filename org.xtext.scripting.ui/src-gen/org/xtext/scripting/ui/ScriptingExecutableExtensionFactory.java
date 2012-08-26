/*
 * generated by Xtext
 */
package org.xtext.scripting.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.xtext.scripting.ui.internal.ScriptingActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ScriptingExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ScriptingActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ScriptingActivator.getInstance().getInjector(ScriptingActivator.ORG_XTEXT_SCRIPTING_SCRIPTING);
	}
	
}
