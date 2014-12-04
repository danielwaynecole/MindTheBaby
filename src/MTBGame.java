import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private static final int ACCEPTABLE_SPACE_USED = 92;
	
	private int squareFootage;
	private int numberOfFloors;
	private double buildingWidth;
	private double buildingLength;
	private double buildingWidthPercentage;
	private double buildingLengthPercentage;
	private int currentLevel = 1;
	private boolean mutateOnce = false;
	private boolean mutateUntilAcceptable = false;
	private House house;
	private double scale;
	private Graphics g;
	private JButton basementButton;
	private JButton firstFloorButton;
	private JButton secondFloorButton;
	private JButton thirdFloorButton;
	private JButton fourthFloorButton;
	private JButton mutateOnceButton;
	private JButton mutateCompleteButton;
	private JButton resetButton;

	Container cp;
	JPanel buttons = new JPanel();

	/**
	 * 
	 */
	public MTBGame() {
		collectUserInput();
		calculateBuildingDimensions();
		addButtons();
	    addWindowListener(new WindowDestroyer());
	    repaint();
	    setVisible(true);
	    setG(getGraphics());
	}

	private void addButtons(){
		cp = getContentPane();
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
	    
	    mutateOnceButton = new JButton("Mutate Once");
	    mutateOnceButton.setActionCommand("mutateOnce");
	    mutateOnceButton.addActionListener(this);
	    buttons.add(mutateOnceButton);
	    
	    mutateCompleteButton = new JButton("Mutate Complete");
	    mutateCompleteButton.setActionCommand("mutateuntilacceptable");
	    mutateCompleteButton.addActionListener(this);
	    buttons.add(mutateCompleteButton);
	    
	    resetButton = new JButton("Reset");
	    resetButton.setActionCommand("reset");
	    resetButton.addActionListener(this);
	    buttons.add(resetButton);
	 
	    cp.add(buttons, BorderLayout.AFTER_LAST_LINE);
	}
	
	/**
	 * 
	 */
	private void collectUserInput(){
		boolean valid = false;
		int squareFootage = 0;
		int numberOfFloors = 0;
		while(!valid){
			String s_squareFootage = JOptionPane.showInputDialog("Please enter square footage (minimum 600)"); 
			String s_numberOfFloors = JOptionPane.showInputDialog("Please enter number of floors (maximum 3)"); 
			boolean invalidInput = false;
			try {
				squareFootage = Integer.parseInt(s_squareFootage); 
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog( null, "Please enter a valid square footage value.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
				invalidInput = true;
			}
			try {
				numberOfFloors = Integer.parseInt(s_numberOfFloors);
			} catch (NumberFormatException e){ 
				invalidInput = true;
				JOptionPane.showMessageDialog( null, "Please enter a valid square footage value.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
			}
			if(s_squareFootage == null){
				JOptionPane.showMessageDialog( null, "Please enter a valid number of floors value.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);				

			}
			if(invalidInput){
				collectUserInput();
				break;
			}
			valid = validateInputs(squareFootage, numberOfFloors);
		}
		this.squareFootage = squareFootage;
		this.numberOfFloors = numberOfFloors;
	}
	
	/**
	 * 
	 * @param squareFootage
	 * @param numberOfFloors
	 * @return
	 */
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
	
	/**
	 * 
	 */
	private void calculateBuildingDimensions(){
		double rNum = randInt(40, 60);
		double ratio = rNum / (100 - rNum);
		double widthPercentage = rNum * .01;
		double lengthPercentage = (100 - rNum) * .01;
		setBuildingWidthPercentage(widthPercentage);
		setBuildingLengthPercentage(lengthPercentage);	
		double buildingWidth = Math.sqrt((squareFootage/numberOfFloors) / ratio);
		double buildingLength = (squareFootage/numberOfFloors) / buildingWidth;
		setBuildingWidth(buildingWidth);
		setBuildingLength(buildingLength);
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g){
		this.paintComponents(g);
		this.setG(g);
		drawBuilding(g);
	}
	
	/**
	 * 
	 * @param g
	 */
	public void drawBuilding(Graphics g){
			double widthScale = (APP_WIDTH / buildingWidth) / 1.2;
			double heightScale = (APP_HEIGHT / buildingLength) / 1.2;
			double width = buildingWidth * widthScale;
			double height = buildingLength * heightScale;
			// if we don't have a house object already or if it's been reset, create a new one
			if(house == null){
				house = new House(g, numberOfFloors, (int) buildingWidth, (int) buildingLength, (Floor.CEILING_HEIGHT*numberOfFloors));
			}
			// if we are mutating a single generation, cloop through floors and mutate each population
			if(mutateOnce){
				for(int i = 1; i <= numberOfFloors; i++){
					Floor mFloor = house.getFloors(i);
					if(mFloor != null){
						house.mutate(i);
					}
				}
				repaint();
				mutateOnce = false;
			// if we are mutating until we meet acceptance criterion, loop through floors and mutate each population
			} else if(mutateUntilAcceptable){
				int combinedFitness = 0;
				for(int i = 1; i <= numberOfFloors; i++){
					Floor mFloor = house.getFloors(i);
					if(mFloor != null){
						while(mFloor.getFitness() < ACCEPTABLE_SPACE_USED){
							house.mutate(i);
						}

					}
				}
				repaint();
				mutateUntilAcceptable = false;
			}
			Floor floor = house.getFloors(currentLevel);
			Room[] rooms = floor.getRooms();
			g.setColor(Color.blue);
			g.drawRect(LEFT_MARGIN, TOP_MARGIN, (int)(buildingWidth*widthScale), (int)(buildingLength*heightScale));
			if(currentLevel == 0){
				BufferedImage image;
				try {
					image = ImageIO.read(new File("img/spider.jpg"));
					g.drawImage(image, LEFT_MARGIN, TOP_MARGIN, (int)width, (int)height, null);
				} catch (IOException ex) {
					int x = (int)(LEFT_MARGIN + width) / 2;
					int y = (int)(TOP_MARGIN + height) / 2;
					g.setColor(Color.red);
					g.drawString("DANGER!", x, y);
				}
			}
			for(int i = 0; i < rooms.length; i++){
				try {
					// room position (scaled to 1024 X 768)
					int xOffset = (int)(LEFT_MARGIN + (rooms[i].getxOffset() * widthScale));
					int yOffset = (int)(TOP_MARGIN + (rooms[i].getyOffset() * heightScale));
					// room dimensions (scaled to 1024 X 768)
					int roomWidth = (int)(rooms[i].getWidth() * widthScale);
					int roomHeight = (int)(rooms[i].getLength() * heightScale);
					// what color to make the rectangle
					g.setColor(rooms[i].getRoomColor());
					g.drawRect(xOffset, yOffset, roomWidth, roomHeight);
					g.fillRect(xOffset+5, yOffset+5, roomWidth-5, roomHeight-5);
					// put the name of the room in the rectangle
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
		if (e.getActionCommand().equals("basement")) {
			this.currentLevel = 0;
			repaint();
		} else if (e.getActionCommand().equals("floor1")) {
			this.currentLevel = 1;
			repaint();
		} else if (e.getActionCommand().equals("floor2")) {
			this.currentLevel = 2;
			repaint();
		} else if (e.getActionCommand().equals("floor3")) {
			this.currentLevel = 3;
			repaint();
		} else if (e.getActionCommand().equals("floor1")) {
			this.currentLevel = 4;
			repaint();
		} else if (e.getActionCommand().equals("mutateOnce")) {
			mutateOnce = true;
			repaint();
		} else if (e.getActionCommand().equals("mutateuntilacceptable")) {
			mutateUntilAcceptable = true;
			repaint();
		} else if (e.getActionCommand().equals("reset")) {
			this.setHouse(null);
			this.currentLevel = 1;
			cp.removeAll();
			buttons.removeAll();
			collectUserInput();
			calculateBuildingDimensions();
			addButtons();
			repaint();
		}	
	}
	
	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
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

	public void setBuildingLengthPercentage(double buildingLengthPercentage) {
		this.buildingLengthPercentage = buildingLengthPercentage;
	}

	public double getBuildingLengthPercentage() {
		return buildingLengthPercentage;
	}

	public void setBuildingWidthPercentage(double buildingWidthPercentage) {
		this.buildingWidthPercentage = buildingWidthPercentage;
	}

	public double getBuildingWidthPercentage() {
		return buildingWidthPercentage;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public Graphics getG() {
		return g;
	}
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
}
