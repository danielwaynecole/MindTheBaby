import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

public class MTBGame extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2436493779463711544L;
	private static final int APP_WIDTH = 1024;
	private static final int APP_HEIGHT = 768;
	private static final int TOP_MARGIN = 50;
	private static final int LEFT_MARGIN = 10;

	
	private static final int MIN_SQUARE_FOOTAGE = 600;
	private static final int MAX_SQUARE_FOOTAGE = 3000;
	private static final int MIN_NUMBER_OF_FLOORS = 1;
	private static final int MAX_NUMBER_OF_FLOORS = 4;
	
	private int squareFootage;
	private int numberOfFloors;
	private double buildingWidth;
	private double buildingLength;
	private double buildingWidthPercentage;
	private double buildingLengthPercentage;
	private double scale;
	private Graphics g;
	private JButton basementButton;
	private JButton firstFloorButton;
	private JButton secondFloorButton;
	private JButton thirdFloorButton;
	private JButton fourthFloorButton;

	
	JPanel buttons = new JPanel();

	public MTBGame() {
		collectUserInput();
		calculateBuildingDimensions();
		
		Container cp = getContentPane();
	    setSize(APP_WIDTH, APP_HEIGHT);
	    setLocation(100,100);
	    setResizable(false);
	    cp.setBackground(Color.GRAY);
	    cp.setLayout(new BorderLayout());
	    cp.addMouseListener(this);

	    buttons.setLayout(new FlowLayout());
	    buttons.setBackground(Color.gray);
	    
	    basementButton = new JButton("Basement");
	    basementButton.setActionCommand("basement");
	    basementButton.addActionListener(this);

	    firstFloorButton = new JButton("Floor 1");
	    firstFloorButton.setActionCommand("floor1");
	    firstFloorButton.addActionListener(this);

	    secondFloorButton = new JButton("Floor 2");
	    secondFloorButton.setActionCommand("floor2");
	    secondFloorButton.addActionListener(this);
	    
	    thirdFloorButton = new JButton("Floor 3");
	    thirdFloorButton.setActionCommand("floor3");
	    thirdFloorButton.addActionListener(this);
	    
	    fourthFloorButton = new JButton("Floor 4");
	    fourthFloorButton.setActionCommand("floor4");
	    fourthFloorButton.addActionListener(this);
	    
	    for(int i = 0; i < this.numberOfFloors + 1; i++){
	    	if(i > numberOfFloors)
	    		break;
	    	switch(i){
	    	case 0:
	    	    buttons.add(basementButton);
	    	    break;
	    	case 1:
	    	    buttons.add(firstFloorButton);
	    	    break;
	    	case 2:
	    	    buttons.add(secondFloorButton);
	    	    break;
	    	case 3:
	    	    buttons.add(thirdFloorButton);
	    	    break;
	    	case 4:
	    	    buttons.add(fourthFloorButton);
	    	    break;	    	    
	    	}
	    }
	    
	    cp.add(buttons, BorderLayout.AFTER_LAST_LINE);

	    addWindowListener(new WindowDestroyer());

	    repaint();
	    setVisible(true);
	    g = getGraphics();
	}

	private void collectUserInput(){
		boolean valid = false;
		int squareFootage = 0;
		int numberOfFloors = 0;
		while(!valid){
			String s_squareFootage = JOptionPane.showInputDialog("Please enter square footage (minimum 600)"); 
			String s_numberOfFloors = JOptionPane.showInputDialog("Please enter number of floors (maximum 3)"); 
			squareFootage = Integer.parseInt(s_squareFootage); 
			numberOfFloors = Integer.parseInt(s_numberOfFloors);
			valid = validateInputs(squareFootage, numberOfFloors);
		}
		this.squareFootage = squareFootage;
		this.numberOfFloors = numberOfFloors;
	}
	
	private boolean validateInputs(int squareFootage, int numberOfFloors){
		boolean valid = true;
		if(squareFootage < MIN_SQUARE_FOOTAGE){
			JOptionPane.showMessageDialog( null, "The square footage must be greater than "+MIN_SQUARE_FOOTAGE+".", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
			valid = false;
		}
		if(squareFootage >= MAX_SQUARE_FOOTAGE){
			JOptionPane.showMessageDialog( null, "The square footage must be less than "+MAX_SQUARE_FOOTAGE+".", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
			valid = false;
		}
		if(numberOfFloors < MIN_NUMBER_OF_FLOORS){
			JOptionPane.showMessageDialog( null, "The number of rooms must be greater than "+MIN_NUMBER_OF_FLOORS+".", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
			valid = false;
		}
		if(numberOfFloors >= MAX_NUMBER_OF_FLOORS){
			JOptionPane.showMessageDialog( null, "The number of rooms must be less than "+MAX_NUMBER_OF_FLOORS+".", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
			valid = false;
		}
		switch(numberOfFloors){
		case 2:
			if(squareFootage < 1000){
				JOptionPane.showMessageDialog( null, "The square footage must be greater than "+1000+" for a home with 2 floors.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
				valid = false;
			}
			break;
		case 3:
			if(squareFootage < 1500){
				JOptionPane.showMessageDialog( null, "The square footage must be greater than "+1000+" for a home with 2 floors.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
				valid = false;
			}
		break;
		case 4:
			if(squareFootage < 2000){
				JOptionPane.showMessageDialog( null, "The square footage must be greater than "+1000+" for a home with 2 floors.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
				valid = false;
			}
		break;
		
		}
		return valid;
	}
	
	private void calculateBuildingDimensions(){
		double rNum = randInt(40, 60);
		double ratio = rNum / (100 - rNum);
		double widthPercentage = rNum * .01;
		double lengthPercentage = (100 - rNum) * .01;
		buildingWidthPercentage = widthPercentage;
		buildingLengthPercentage = lengthPercentage;	
		double buildingWidth = Math.sqrt((squareFootage/numberOfFloors) / ratio);
		double buildingLength = (squareFootage/numberOfFloors) / buildingWidth;
		setBuildingWidth(buildingWidth);
		setBuildingLength(buildingLength);
	}
	
	public void paint(Graphics g){
		this.paintComponents(g);
		this.g = g;
		drawBuilding(g);
	}
	
	public void drawBuilding(Graphics g){
			double widthScale = (APP_WIDTH / buildingWidth) / 1.2;
			double heightScale = (APP_HEIGHT / buildingLength) / 1.2;
			double width = buildingWidth * widthScale;
			double height = buildingLength * heightScale;
			
			//g.setColor(Color.blue);
			//g.drawRect(LEFT_MARGIN, TOP_MARGIN, (int)width, (int)height);
			House house = new House(g, numberOfFloors, (int) buildingWidth, (int) buildingLength, (Floor.CEILING_HEIGHT*numberOfFloors));
			Floor[] floors = house.getFloors();
			Room[] rooms = floors[1].getRooms();
			g.setColor(Color.blue);
			//g.drawRect(LEFT_MARGIN, TOP_MARGIN, (int)buildingWidth, (int)buildingLength);
			g.drawRect(LEFT_MARGIN, TOP_MARGIN, (int)(buildingWidth*widthScale), (int)(buildingLength*heightScale));
			for(int i = 0; i < rooms.length; i++){
				try {
					int xOffset = (int)(LEFT_MARGIN + (rooms[i].getxOffset() * widthScale));
					int yOffset = (int)(TOP_MARGIN + (rooms[i].getyOffset() * heightScale));
					int roomWidth = (int)(rooms[i].getWidth() * widthScale);
					int roomHeight = (int)(rooms[i].getLength() * heightScale);
					System.out.println("x offset: " + xOffset + "; y offset: " + yOffset + "; room width: " + roomWidth + "; room height: " + roomHeight );
					g.setColor(rooms[i].getRoomColor());
					//g.drawRect(LEFT_MARGIN + rooms[i].getxOffset(), TOP_MARGIN + rooms[i].getyOffset(), rooms[i].getWidth(), rooms[i].getLength());
					//g.fillRect(LEFT_MARGIN + rooms[i].getxOffset(), TOP_MARGIN + rooms[i].getyOffset(), rooms[i].getWidth(), rooms[i].getLength());
					g.drawRect(xOffset, yOffset, roomWidth, roomHeight);
					g.fillRect(xOffset+5, yOffset+5, roomWidth-5, roomHeight-5);
					int stringX =  xOffset + 25;
					int stringY =  yOffset + 25;
					g.setColor(Color.blue);
					g.drawString(rooms[i].getRoomName(), stringX, stringY);
				} catch (NullPointerException npe){
					break;
				}
			}
			
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public void setBuildingWidth(double buildingWidth) {
		this.buildingWidth = buildingWidth;
	}

	public double getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingLength(double buildingLength) {
		this.buildingLength = buildingLength;
	}

	public double getBuildingLength() {
		return buildingLength;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getScale() {
		return scale;
	}
}
