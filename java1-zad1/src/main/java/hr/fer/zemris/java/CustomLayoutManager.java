package hr.fer.zemris.java;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class CustomLayoutManager implements LayoutManager {
	
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getMinimumSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        int width = parent.getWidth();
        int height = parent.getHeight();

        Component left = parent.getComponent(0);
        Component right = parent.getComponent(1);

        left.setBounds(0, 0, 200, height);
        right.setBounds(200, 0, width - 200, height);
    }

}
