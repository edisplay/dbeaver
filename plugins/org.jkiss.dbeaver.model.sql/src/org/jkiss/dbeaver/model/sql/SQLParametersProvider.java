/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jkiss.dbeaver.model.sql;

import org.jkiss.code.NotNull;

import java.util.List;

/**
 * Provides values for query parameters.
 * In UI mode shows dialog.
 */
public interface SQLParametersProvider {

    /**
     * @return true if parameters processed, false if use canceled query, null if parameters must be ignored till the end of script.
     */
    Boolean prepareStatementParameters(@NotNull SQLScriptContext scriptContext, @NotNull SQLQuery sqlStatement, @NotNull List<SQLQueryParameter> parameters, boolean useDefaults);

}
