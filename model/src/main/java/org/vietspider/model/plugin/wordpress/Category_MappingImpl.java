package org.vietspider.model.plugin.wordpress;


import org.vietspider.model.plugin.wordpress.XMLWordPressConfig.Category;
import org.vietspider.parser.xml.XMLNode;
import org.vietspider.serialize.Object2XML;
import org.vietspider.serialize.SerializableMapping;
import org.vietspider.serialize.XML2Object;
import org.vietspider.token.attribute.Attributes;


public class Category_MappingImpl implements SerializableMapping<Category> {

	private final static int code=1023040;

	public Category create() {
		return new Category();
	}

	public void toField(Category object, XMLNode node, String name, String value) throws Exception {
		if(name.equals("category-id")) {
			object.setCategoryId(XML2Object.getInstance().toValue(String.class, value));
			return;
		}
		if(name.equals("category-name")) {
			object.setCategoryName(XML2Object.getInstance().toValue(String.class, value));
			return;
		}

	}

	public XMLNode toNode(Category object) throws Exception {
		XMLNode node = new XMLNode("category");
		Attributes attrs  = new Attributes(node);
		Object2XML mapper = Object2XML.getInstance();
		mapper.addNode(object.getCategoryId(), node, false, "category-id");
		mapper.addNode(object.getCategoryName(), node, false, "category-name");
		return node;
	}
}
