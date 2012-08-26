/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.xtext.scripting.scoping;

import static com.google.common.collect.Iterables.*;
import static org.eclipse.xtext.scoping.Scopes.*;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.scoping.LocalVariableScopeContext;
import org.eclipse.xtext.xbase.scoping.XbaseScopeProvider;
import org.xtext.scripting.scripting.Script;

import com.google.common.collect.Lists;

/**
 * @author tobiasjohansson - Initial contribution and API
 */
public class ScriptingScopeProvider extends XbaseScopeProvider {

	@Override
	protected IScope createLocalVarScope(IScope parentScope, LocalVariableScopeContext scopeContext) {
		if (scopeContext.getContext() instanceof Script) {
			Script xScript = (Script) scopeContext.getContext();
			SimpleScope scope = new SimpleScope(allVariables(xScript));
			for (IEObjectDescription elem : scope.getAllElements()) {
				System.out.println( elem.getName() + ", " );
			}
			return scope; 
		}else{
			return super.createLocalVarScope(parentScope, scopeContext);
		}
	}

	private Iterable<IEObjectDescription> allVariables(Script xScript) {
		ResourceSet resourceSet = xScript.eResource().getResourceSet();
		if(xScript.eResource() == null || resourceSet == null){
			return Collections.emptyList();
		}
		List<XVariableDeclaration> variables = Lists.newArrayList();
		for (Resource resource : resourceSet.getResources()) {
			for (Script script : filter(resource.getContents(), Script.class)) {
				addAll(variables, filter(script.getExpressions(), XVariableDeclaration.class));
			}
		}
		return scopedElementsFor(variables);
	}
}