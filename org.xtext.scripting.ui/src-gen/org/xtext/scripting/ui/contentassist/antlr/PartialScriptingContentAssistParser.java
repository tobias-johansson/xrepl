/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.xtext.scripting.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.ui.codetemplates.ui.partialEditing.IPartialContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.util.PolymorphicDispatcher;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class PartialScriptingContentAssistParser extends ScriptingParser implements IPartialContentAssistParser {

	private AbstractRule rule;

	public void initializeFor(AbstractRule rule) {
		this.rule = rule;
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		if (rule == null || rule.eIsProxy())
			return Collections.emptyList();
		String methodName = "entryRule" + rule.getName();
		PolymorphicDispatcher<Collection<FollowElement>> dispatcher = 
			new PolymorphicDispatcher<Collection<FollowElement>>(methodName, 0, 0, Collections.singletonList(parser));
		dispatcher.invoke();
		return parser.getFollowElements();
	}

}