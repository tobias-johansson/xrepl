/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.xtext.scripting;

import java.util.List;

/**
 * @author tobiasjohansson - Initial contribution and API
 */
public class MyListExtensions {

	public static <T> T getFirst(List<T> list) {
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
