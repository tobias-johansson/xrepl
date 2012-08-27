/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.xtext.scripting.scoping;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.xbase.scoping.XbaseImportedNamespaceScopeProvider;

import com.google.common.collect.Lists;

/**
 * @author tobiasjohansson - Initial contribution and API
 */
public class ScriptingImportedNamespaceScopeProvider extends XbaseImportedNamespaceScopeProvider {

	
	@Override
	protected List<ImportNormalizer> getImportedNamespaceResolvers(final EObject context, final boolean ignoreCase) {

		List<ImportNormalizer> allResolvers = Lists.newArrayList();

		ResourceSet resourceSet = context.eResource().getResourceSet();
		for (Resource resource : resourceSet.getResources()) {
			for (EObject content : resource.getContents()) {
				List<ImportNormalizer> resolvers = super.getImportedNamespaceResolvers(content, ignoreCase);
				allResolvers.addAll(resolvers);
				
			}
			
		}
		return allResolvers;
	}

}
