/**
 * Java Class : IPropertyContent.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 *
 * @category   Util
 * @package    com.modeliosoft.modelio.sysml.utils
 *   @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 */
package fr.softeam.toscadesigner.handlers.propertypages;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface defines the contract of all property pages
 */
@objid ("f25b3273-075f-4c94-baaa-50934af8e7eb")
public interface IPropertyContent {
    /**
     * This method handles the changes of the given property, identified by its row
     * index, of a selected element to a new value.
     * @param row : the row of the changed property
     * @param value : the new value of the property
     */
    @objid ("b5050450-a43e-4679-839c-3af03a393b4b")
    void changeProperty(int row, String value);

    /**
     * This method handles the construction of the property table of a selected
     * element
     * @param table : the property table to fulfill
     */
    @objid ("ee4e41b7-0ffb-466e-8c19-e09c89d7149d")
    void update(IModulePropertyTable table);
}

