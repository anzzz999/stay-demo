package designPatterns.factorypattern;

import org.junit.platform.commons.util.StringUtils;

public class ShapeFactory {

    public Shape getShape(String shapeType){
        if (StringUtils.isBlank(shapeType)){
            return null;
        }
        shapeType = shapeType.toLowerCase();
        switch (shapeType){
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            case "square":
                return new Square();
            default:
                return null;

        }
    }

}
