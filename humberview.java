package mao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class humberview extends JFrame {

	private JPanel contentPane;
	private JButton mao = new JButton("");
	private JLabel healthbar = new JLabel("");
	private JLabel car = new JLabel("");
	private JLabel car2 = new JLabel("");
	private ImageIcon caricon;
	private ImageIcon car2icon;
	private ImageIcon car3icon;
	private ImageIcon car4icon;
	private ImageIcon flipcaricon;
	private ImageIcon flipcar2icon;
	private ImageIcon flipcar3icon;
	private ImageIcon flipcar4icon;
	private int width;
	private int height;
	private int count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					humberview frame = new humberview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 * @throws InterruptedException 
	 */
	public humberview() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)screenSize.getWidth();
		height = (int)screenSize.getHeight();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500, 800);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);



		Scanner x = new Scanner(new File("src/userdata/currentaccount.txt"));
		Scanner in = new Scanner(new File("src/userdata/" + x.nextLine() + ".txt"));
		in.nextLine();
		in.nextLine();
		String scorenum = in.nextLine();
		float volume = Float.parseFloat(in.nextLine())/100;
		System.out.println(volume);
		in.reset();

		File soundFile = new File("audio/walk.wav");
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);

		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum()/2;
		float gain = (range * volume) + gainControl.getMinimum()/2;
		gainControl.setValue(gain);

		if(volume == 0) {
			gainControl.setValue(gainControl.getMinimum());
		}
		else {

			gainControl.setValue(gain);
		}

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker tracker = new MediaTracker(this);

		Image headimg = toolkit.getImage("images/head.png");
		tracker.addImage(headimg, 0);
		tracker.waitForAll();
		headimg = headimg.getScaledInstance((int)(width/22), (int)(height/10), Image.SCALE_SMOOTH);
		ImageIcon headicon = new ImageIcon(headimg);

		JLabel head = new JLabel("");
		head.setIcon(headicon);
		head.setBounds((int)(width*0.01), (int)(height*-0.43), width, height);
		contentPane.add(head);

		Image healthbarimg = toolkit.getImage("images/healthbar.png");
		tracker.addImage(healthbarimg, 0);
		tracker.waitForAll();
		healthbarimg = healthbarimg.getScaledInstance((int)(width/4), (int)(height/8), Image.SCALE_SMOOTH);
		ImageIcon healthbaricon = new ImageIcon(healthbarimg);

		healthbar.setIcon(healthbaricon);
		healthbar.setBounds((int)(width*0.02), (int)(height*-0.43), (int)((width/4)), height);
		contentPane.add(healthbar);

		Image hudimg = toolkit.getImage("images/HUD.png");
		tracker.addImage(hudimg, 0);
		tracker.waitForAll();
		hudimg = hudimg.getScaledInstance((int)(width/4), (int)(height/8), Image.SCALE_SMOOTH);
		ImageIcon hudicon = new ImageIcon(hudimg);

		JLabel hud = new JLabel("");
		hud.setIcon(hudicon);
		hud.setBounds((int)(width*0.02), (int)(height*-0.43), width, height);
		contentPane.add(hud);

		JLabel score = new JLabel("score: " + scorenum);
		score.setForeground(Color.WHITE);
		score.setFont(new Font("Comic Sans MS", Font.PLAIN, height/30));
		score.setBounds((int)(width*0.3), (int)(height*-0.43), width, height);
		contentPane.add(score);

		Image carimg = toolkit.getImage("images/car.png");
		tracker.addImage(carimg, 0);
		tracker.waitForAll();
		carimg = carimg.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon caricon = new ImageIcon(carimg);

		Image car2img = toolkit.getImage("images/car2.png");
		tracker.addImage(car2img, 0);
		tracker.waitForAll();
		car2img = car2img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon car2icon = new ImageIcon(car2img);

		Image car3img = toolkit.getImage("images/car3.png");
		tracker.addImage(car3img, 0);
		tracker.waitForAll();
		car3img = car3img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon car3icon = new ImageIcon(car3img);

		Image car4img = toolkit.getImage("images/car4.png");
		tracker.addImage(car4img, 0);
		tracker.waitForAll();
		car4img = car4img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon car4icon = new ImageIcon(car4img);

		Image flipcarimg = toolkit.getImage("images/flipcar.png");
		tracker.addImage(flipcarimg, 0);
		tracker.waitForAll();
		flipcarimg = flipcarimg.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon flipcaricon = new ImageIcon(flipcarimg);

		Image flipcar2img = toolkit.getImage("images/flipcar2.png");
		tracker.addImage(flipcar2img, 0);
		tracker.waitForAll();
		flipcar2img = flipcar2img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon flipcar2icon = new ImageIcon(flipcar2img);

		Image flipcar3img = toolkit.getImage("images/flipcar3.png");
		tracker.addImage(flipcar3img, 0);
		tracker.waitForAll();
		flipcar3img = flipcar3img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon flipcar3icon = new ImageIcon(flipcar3img);

		Image flipcar4img = toolkit.getImage("images/flipcar4.png");
		tracker.addImage(flipcar4img, 0);
		tracker.waitForAll();
		flipcar4img = flipcar4img.getScaledInstance((int)(width/3), (int)(height/3), Image.SCALE_SMOOTH);
		ImageIcon flipcar4icon = new ImageIcon(flipcar4img);

		car2.setIcon(flipcar2icon);
		car2.setBounds((int)(width*-3), (int)(height*0.8), (int)(width/3), (int)(height/3));
		contentPane.add(car2);

		car.setIcon(caricon);
		car.setBounds((int)(width*2), (int)(height*0.675), (int)(width/3), (int)(height/3));
		contentPane.add(car);

		Image interactimg = toolkit.getImage("images/interactwhite.png");
		tracker.addImage(interactimg, 0);
		tracker.waitForAll();
		interactimg = interactimg.getScaledInstance((int)(width/5), height/10, Image.SCALE_SMOOTH);
		ImageIcon interacticon = new ImageIcon(interactimg);

		JLabel interact = new JLabel("");
		interact.setIcon(interacticon);
		interact.setBounds(0, 0, width/5, (int)(height/10));
		contentPane.add(interact);
		interact.setVisible(false);

		Image runrightimg = toolkit.getImage("images/runright.gif");
		tracker.addImage(runrightimg, 0);
		tracker.waitForAll();
		runrightimg = runrightimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon runrighticon = new ImageIcon(runrightimg);

		Image runleftimg = toolkit.getImage("images/run.gif");
		tracker.addImage(runleftimg, 0);
		tracker.waitForAll();
		runleftimg = runleftimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon runlefticon = new ImageIcon(runleftimg);

		Image maorightimg = toolkit.getImage("images/spriteflipped.png");
		tracker.addImage(maorightimg, 0);
		tracker.waitForAll();
		maorightimg = maorightimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon maorighticon = new ImageIcon(maorightimg);

		Image maoleftimg = toolkit.getImage("images/sprite1.png");
		tracker.addImage(maoleftimg, 0);
		tracker.waitForAll();
		maoleftimg = maoleftimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon maolefticon = new ImageIcon(maoleftimg);

		Image attackrightimg = toolkit.getImage("images/attackright.gif");
		tracker.addImage(attackrightimg, 0);
		tracker.waitForAll();
		attackrightimg = attackrightimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon attackrighticon = new ImageIcon(attackrightimg);

		Image attackleftimg = toolkit.getImage("images/attack.gif");
		tracker.addImage(attackleftimg, 0);
		tracker.waitForAll();
		attackleftimg = attackleftimg.getScaledInstance((int)(width/7.5), height/4, Image.SCALE_DEFAULT);
		ImageIcon attacklefticon = new ImageIcon(attackleftimg);

		mao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("x:" + mao.getX());
				System.out.print(" y:" + mao.getY());
				System.out.print(" w:" + (double)mao.getX()/width);
				System.out.print(" h:" + (double)mao.getY()/height);
			}
		});
		mao.setBorderPainted(false); 
		mao.setContentAreaFilled(false); 
		mao.setFocusPainted(false); 
		mao.setOpaque(false);
		mao.setBounds((int)(width*0.9), (int)(height*0.6),(int)(width/9), height/4);

		mao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {


				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					mao.setIcon(runlefticon);

					if(mao.getX() < -200)
					{
						clip.stop();
						outside2.main(null);
						delay(1000);
						dispose();
					}
					else
					{
						mao.setBounds((int)(mao.getX()-width*0.01), mao.getY(), mao.getWidth(), mao.getHeight());
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					mao.setIcon(runrighticon);

					if(mao.getX() > (int)(width*0.95))
					{
						mao.setBounds(mao.getX(),mao.getY(), mao.getWidth(), mao.getHeight());
					}
					else
					{
						mao.setBounds((int)(mao.getX()+width*0.01), mao.getY(), mao.getWidth(), mao.getHeight());
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					if(mao.getIcon().equals(maolefticon))
					{
						mao.setIcon(runlefticon);
					}
					if(mao.getIcon().equals(maorighticon))
					{
						mao.setIcon(runrighticon);
					}
					if(mao.getY() <= (int)(height*0.6))
					{
						mao.setBounds(mao.getX(), (mao.getY()), mao.getWidth(), mao.getHeight());
					}
					else
					{
						mao.setBounds(mao.getX(), (int)(mao.getY()-height*0.02), mao.getWidth(), mao.getHeight());
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN){
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					if(mao.getIcon().equals(maolefticon))
					{
						mao.setIcon(runlefticon);
					}
					if(mao.getIcon().equals(maorighticon))
					{
						mao.setIcon(runrighticon);
					}
					else if(mao.getY() > height-mao.getHeight())
					{
						mao.setBounds(mao.getX(), mao.getY(), mao.getWidth(), mao.getHeight());
					}
					else
					{
						mao.setBounds(mao.getX(), (int)(mao.getY()+height*0.02), mao.getWidth(), mao.getHeight());
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_X) {
					if(mao.getIcon().equals(maolefticon))
					{
						mao.setIcon(attacklefticon);
					}
					if(mao.getIcon().equals(maorighticon))
					{
						mao.setIcon(attackrighticon);
					}
				}
				if(mao.getX() > (int)(width*0.01) && mao.getX() < (int)(width*0.1) && (mao.getY() <= (int)(height*0.6)))
				{
					interact.setVisible(true);
					interact.setBounds((int)(width*0.01), (int)(height*0.5), interact.getWidth(), interact.getHeight());
					if (e.getKeyCode() == KeyEvent.VK_Z)
					{		
						school.main(null);
						dispose();
					}
				}
				else
				{
					interact.setVisible(false);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					clip.stop();
					mao.setIcon(maolefticon);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT){
					clip.stop();
					mao.setIcon(maorighticon);
				}
				if (e.getKeyCode() == KeyEvent.VK_UP){
					clip.stop();
					if(mao.getIcon().equals(runlefticon))
					{
						mao.setIcon(maolefticon);
					}
					if(mao.getIcon().equals(runrighticon))
					{
						mao.setIcon(maorighticon);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN){
					clip.stop();
					if(mao.getIcon().equals(runlefticon))
					{
						mao.setIcon(maolefticon);
					}
					if(mao.getIcon().equals(runrighticon))
					{
						mao.setIcon(maorighticon);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_X) {
					if(mao.getIcon().equals(attacklefticon) || mao.getIcon().equals(maolefticon))
					{
						mao.setIcon(maolefticon);
					}
					if(mao.getIcon().equals(attackrighticon) || mao.getIcon().equals(maorighticon))
					{
						mao.setIcon(maorighticon);
					}
				}

			}
		});

		mao.setIcon(maolefticon);
		contentPane.add(mao);

		Image backgroundimg = toolkit.getImage("images/humberview.png");
		tracker.addImage(backgroundimg, 0);
		tracker.waitForAll();
		backgroundimg = backgroundimg.getScaledInstance(width,height, Image.SCALE_SMOOTH);
		ImageIcon backgroundicon = new ImageIcon(backgroundimg);

		JLabel background = new JLabel("");
		background.setIcon(backgroundicon);
		background.setBounds(0, 0, width, height);
		contentPane.add(background);

		JLabel clouds = new JLabel("");
		clouds.setIcon(new ImageIcon("images/clouds.gif"));
		clouds.setBounds(0, 0, width, height);
		contentPane.add(clouds);

		ImageIcon[][] cararray = 
			{
					{caricon, car2icon, car3icon, car4icon},
					{flipcaricon, flipcar2icon, flipcar3icon, flipcar4icon},
			};

		moveCar1(cararray);
		moveCar2(cararray);
	}

	public void moveCar1(ImageIcon[][]cararray) {
		ActionListener movecar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if hit by car
				if(car.getX() < mao.getX()  && car.getX() >= mao.getX()-(width*0.01) && mao.getY() >= (int)(height*0.675))
				{
					count = 0;
					//damageAnimation();
					//lowers health
					healthbar.setBounds(healthbar.getX(), healthbar.getY(), healthbar.getWidth() - (int)(width*0.1), healthbar.getHeight());
					if(healthbar.getWidth() <= 0)
					{
						car.setBounds((int)(width*2.1), car.getY(), car.getWidth(), car.getHeight());
						try {
							outside x = new outside();
							x.music.stop();
						}catch(Exception e1){}
						delay(1500);
						gameover.main(null);
						return;
					}
				}
				//if car is off screen
				if(car.getX() < -width)
				{
					int randomNum = ThreadLocalRandom.current().nextInt(0, (cararray[0].length-1) + 1);
					car.setIcon(cararray[0][randomNum]);
					car.setBounds((int)(width*2.1), car.getY(), car.getWidth(), car.getHeight());
				}
				car.setBounds((int) (car.getX() - (width*0.01)), car.getY(), car.getWidth(), car.getHeight());
			}	
		};
		Timer cartimer = new Timer(10, movecar);
		cartimer.setRepeats(true);
		cartimer.start();
	}

	public void moveCar2(ImageIcon[][]cararray) {
		ActionListener movecar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(car2.getX() > width)
				{
					int randomNum = ThreadLocalRandom.current().nextInt(0, (cararray[0].length-1) + 1);
					car2.setIcon(cararray[1][randomNum]);
					car2.setBounds((int)(width*-3.1), car2.getY(), car2.getWidth(), car2.getHeight());
				}
				car2.setBounds((int) (car2.getX() + (width*0.01)), car2.getY(), car2.getWidth(), car2.getHeight());
			}	
		};

		Timer cartimer = new Timer(10, movecar);
		cartimer.setRepeats(true);
		cartimer.start();
	}

	public void delay(int time) {
		ActionListener delay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}	
		};
		Timer timer = new Timer(time, delay);
		timer.setRepeats(false);
		timer.start();
	}

}
