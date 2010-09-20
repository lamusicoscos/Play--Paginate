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
package play.modules.paginate;

import java.io.Serializable;
import java.util.List;

import play.db.jpa.Model;
import play.modules.paginate.locator.JPAIndexedRecordLocator;
import play.modules.paginate.locator.JPAKeyedRecordLocator;

public class ModelPaginator<T extends Model> extends Paginator<Long, T> implements
		Serializable {
	private static final long serialVersionUID = -2064492602195638937L;

	private transient KeyedRecordLocator<Long, T> keyedRecordLocator;
	private transient IndexedRecordLocator<Long, T> indexedRecordLocator;
	
	public ModelPaginator(Class<T> typeToken, List<Long> keys, int pageSize) {
		super(typeToken, keys, pageSize);
	}

	public ModelPaginator(Class<T> typeToken, List<Long> keys) {
		super(typeToken, keys);
	}

	public ModelPaginator(List<T> values) {
		super(values);
	}

	public ModelPaginator(Class<T> typeToken, long rowCount) {
		super(typeToken, (int)rowCount);
	}

	public ModelPaginator(Class<T> typeToken, long rowCount, int pageSize) {
		super(typeToken, (int)rowCount, pageSize);
	}

	protected ModelPaginator() {}
	
	@Override
	protected KeyedRecordLocator<Long, T> getKeyedRecordLocator() {
		if (typeToken == null)
			throw new IllegalStateException(
					"Record locators are only used when the list paginates over keys; a type token is required");
		if (keyedRecordLocator == null) {
			keyedRecordLocator = new JPAKeyedRecordLocator<Long, T>(typeToken);
		}
		return keyedRecordLocator;
	}

	@Override
	protected IndexedRecordLocator<Long, T> getIndexedRecordLocator() {
		if (typeToken == null)
			throw new IllegalStateException(
					"Record locators are only used when the list paginates over keys; a type token is required");
		if (indexedRecordLocator == null) {
			indexedRecordLocator = new JPAIndexedRecordLocator<Long, T>(typeToken);
		}
		return indexedRecordLocator;
	}
}
