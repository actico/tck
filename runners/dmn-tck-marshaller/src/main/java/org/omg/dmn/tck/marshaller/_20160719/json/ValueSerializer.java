/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.omg.dmn.tck.marshaller._20160719.json;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import org.w3c.dom.Element;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ValueSerializer extends JsonSerializer<Object>
{

   @Override
   public void serialize(Object value, JsonGenerator gen,
      SerializerProvider serializers) throws IOException,
      JsonProcessingException
   {
      if (value instanceof Element)
      {
         Element element = (Element) value;
         String content = element.getTextContent();
         gen.writeString(content);
      }
      else if (value instanceof JAXBElement<?>)
      {
         Object elemValue = ((JAXBElement<?>) value).getValue();
         if (elemValue instanceof Element)
         {
            serialize(((JAXBElement<?>) value).getValue(), gen, serializers);
         }
         else
         {
            gen.writeObject(elemValue);
         }
      }
      else
      {
         gen.writeObject(value);
      }
   }

}
