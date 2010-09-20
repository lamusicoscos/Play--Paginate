/**
 *
 * Copyright 2010, Lawrence McAlpin.
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package play.modules.paginate.locator;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import play.db.jpa.JPA;
import play.modules.paginate.IndexedRecordLocator;

public class JPAIndexedRecordLocator<K,Model> implements IndexedRecordLocator<K,Model> {
	private Class<Model> typeToken;
	
	public JPAIndexedRecordLocator(Class<Model> typeToken) {
		this.typeToken = typeToken;
	}
	
	@Override
	public List<Model> findByIndex(int firstResult, int pageSize) {
		@SuppressWarnings("unchecked")
		List<Model> returnMe = JPA.em().createQuery("FROM " + typeToken.getSimpleName()).setFirstResult(firstResult).setMaxResults(pageSize).getResultList();
		return returnMe;
	}

}
