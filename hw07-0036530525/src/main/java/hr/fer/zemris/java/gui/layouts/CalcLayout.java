package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class CalcLayout implements LayoutManager2{
	
	private int gap;
	private double maxPreferredHeight = 0;
	private double maxPreferredWidth = 0;
	private double maxMinWidth = 0;
	private double maxMinHeight = 0;
	private double maxWidth = 0;
	private double maxHeight;
	private Map<Component, RCPosition> map = new HashMap<>();
	
	public CalcLayout() {
		this(0);
	}
	
	public CalcLayout(int gap) {
		this.gap = gap;
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		throw new UnsupportedOperationException();
	}
	
	private void updateSizes(double width, double height) {
		if(maxPreferredWidth < width)
			maxPreferredWidth = width;
		if(maxPreferredHeight < height)
			maxPreferredHeight = height;
		if(maxMinWidth < width)
			maxMinWidth = width;
		if(maxMinHeight < height)
			maxMinHeight = height;
		if(maxWidth < width)
			maxWidth = width;
		if(maxHeight < height)
			maxHeight = height;
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		map.remove(comp);
	}
	
	private BiFunction<Dimension, Insets, Dimension> izracunaj = (d, i) -> {
		Dimension ret = new Dimension();
		ret.width = 7 * d.width + 6 * gap + i.left + i.right;
		ret.height = 5 * d.height + 4 * gap + i.top + i.bottom;
		return ret;
	};

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Insets insets = parent.getInsets();
		return izracunaj.apply(new Dimension((int) Math.ceil(maxPreferredWidth), (int)Math.ceil(maxPreferredHeight)), insets);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		Insets insets = parent.getInsets();
		return izracunaj.apply(new Dimension((int) Math.ceil(maxMinWidth), (int)Math.ceil(maxMinHeight)), insets);
	}

	@Override
	public void layoutContainer(Container parent) {
		
		double height = (parent.getSize().height - 4.0 * gap) / 5;
		double width = (parent.getSize().width - 6.0 * gap) / 7;
		
		for (Component c : map.keySet()) {
			if (map.get(c).getRow() == 1 && map.get(c).getColumn() == 1)
				c.setBounds((int) Math.round((map.get(c).getColumn() - 1) * (width + gap)) , 
						(int) Math.round((map.get(c).getRow() - 1) * (height + gap)), 
						(int) Math.round(width * 5 + gap * 4), 
						(int) Math.round(height));
			else
				c.setBounds((int)Math.round((map.get(c).getColumn() - 1) * (width + gap)) , 
						(int)Math.round((map.get(c).getRow() - 1) * (height + gap)), 
						(int) Math.round(width), 
						(int) Math.round(height));
		}
		
		
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		RCPosition position;
		
		if(constraints == null)
			throw new NullPointerException();
		
		if (constraints instanceof RCPosition)
			position = (RCPosition) constraints;
		else if(constraints instanceof String) 
			position = RCPosition.parse((String) constraints);
		 else
			throw new IllegalArgumentException();
		
		if(map.containsValue(position) || position.getRow() <= 0 || position.getRow() >= 6 
				|| position.getColumn() <= 0 || position.getColumn() >=8) {
			throw new CalcLayoutException();
		}
			
		map.put(comp, position);
		
		Dimension d = comp.getPreferredSize();
		if (((RCPosition) constraints).getRow() == 1 && ((RCPosition) constraints).getColumn() == 1)
			updateSizes((d.getWidth() - 4 * gap) / 5, d.getHeight());
		else
			updateSizes(d.getWidth(), d.getHeight());
		
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		Insets insets = target.getInsets();
		return izracunaj.apply(new Dimension((int) Math.ceil(maxWidth), (int)Math.ceil(maxHeight)), insets);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
