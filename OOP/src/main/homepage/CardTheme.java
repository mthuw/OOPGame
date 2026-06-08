package main.homepage;

import java.awt.*;

public enum CardTheme {
    GREEN(new Color(156, 197, 161)),
    GOLD(new Color(245, 236, 212));
    private final Color bgColor;

    CardTheme(Color bgColor){
        this.bgColor = bgColor;
    }
    public Color getBgColor(){
        return bgColor;
    }
}
