/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 *
 * @author Admin
 */
public class ChessServer {
    
    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static Piece selectedPiece1 = null;
    public static int turnplay = 1;
    Piece ExQueen[] = new Piece[16];
    int NumExwQueen = 0;
    int NumExbQueen = 8;
    BufferedImage all;
    Timer thoigian;
    String temp="";
    Integer second;
    JLabel demthoigian;
    JTextArea content;
    JTextField enterchat;
    JButton send;
    ServerSocket serversocket;
    Socket socket;
    OutputStream os;
    InputStream is;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    MenuBar menubar;
    
    public ChessServer() throws IOException{
        this.all = ImageIO.read(new File("C:\\Users\\Admin\\Downloads\\chess.png"));
        Image imgs[] = new Image[13];
        int ind = 0;
        for(int y = 0; y < 400; y += 200){
            for(int x = 0; x < 1200; x += 200){
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind ++;
            }
        }
        
        Piece wKing = new Piece(4, 7, 1, "King", ps,0);
        Piece wQueen = new Piece(3, 7, 1, "Queen", ps,0);
        Piece wBishop1 = new Piece(2, 7, 1, "Bishop", ps,0);
        Piece wBishop2 = new Piece(5, 7, 1, "Bishop", ps,0);
        Piece wKnight1 = new Piece(1, 7, 1, "Knight", ps,0);
        Piece wKnight2 = new Piece(6, 7, 1, "Knight", ps,0);
        Piece wRook1 = new Piece(0, 7, 1, "Rook", ps,0);
        Piece wRook2 = new Piece(7, 7, 1, "Rook", ps,0);
        Piece wPawn1 = new Piece(0, 6, 1, "Pawn", ps,0);
        Piece wPawn2 = new Piece(1, 6, 1, "Pawn", ps,0);
        Piece wPawn3 = new Piece(2, 6, 1, "Pawn", ps,0);
        Piece wPawn4 = new Piece(3, 6, 1, "Pawn", ps,0);
        Piece wPawn5 = new Piece(4, 6, 1, "Pawn", ps,0);
        Piece wPawn6 = new Piece(5, 6, 1, "Pawn", ps,0);
        Piece wPawn7 = new Piece(6, 6, 1, "Pawn", ps,0);
        Piece wPawn8 = new Piece(7, 6, 1, "Pawn", ps,0);
        
        Piece bKing = new Piece(4, 0, 0, "King", ps,0);
        Piece bQueen = new Piece(3, 0, 0, "Queen", ps,0);
        Piece bBishop1 = new Piece(2, 0, 0, "Bishop", ps,0);
        Piece bBishop2 = new Piece(5, 0, 0, "Bishop", ps,0);
        Piece bKnight1 = new Piece(1, 0, 0, "Knight", ps,0);
        Piece bKnight2 = new Piece(6, 0, 0, "Knight", ps,0);
        Piece bRook1 = new Piece(0, 0, 0, "Rook", ps,0);
        Piece bRook2 = new Piece(7, 0, 0, "Rook", ps,0);
        Piece bPawn1 = new Piece(0, 1, 0, "Pawn", ps,0);
        Piece bPawn2 = new Piece(1, 1, 0, "Pawn", ps,0);
        Piece bPawn3 = new Piece(2, 1, 0, "Pawn", ps,0);
        Piece bPawn4 = new Piece(3, 1, 0, "Pawn", ps,0);
        Piece bPawn5 = new Piece(4, 1, 0, "Pawn", ps,0);
        Piece bPawn6 = new Piece(5, 1, 0, "Pawn", ps,0);
        Piece bPawn7 = new Piece(6, 1, 0, "Pawn", ps,0);
        Piece bPawn8 = new Piece(7, 1, 0, "Pawn", ps,0);
        
        for(int i = 0; i<=7; i++){
            ExQueen[i] = new Piece(8, 8, 1, "Queen", ps,0);
        }
        
        for(int i = 8; i<=15; i++){
            ExQueen[i] = new Piece(8, 8, 0, "Queen", ps,0);
        }
        
        JFrame frame = new JFrame();
        frame.setTitle("Game Chess server");
        frame.setBounds(10, 10, 862, 592);
        frame.setUndecorated(false);
        frame.setResizable(false);
        
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menubar = new MenuBar();
        frame.setMenuBar(menubar);
        Menu game = new Menu("Game");
        menubar.add(game);
        Menu help = new Menu("Help");
        menubar.add(help);
        MenuItem helpItem = new MenuItem("Help");
        help.add(helpItem);
        MenuItem about = new MenuItem("About ..");
        help.add(about);
        help.addSeparator();
        MenuItem surrender = new MenuItem("Surrender");
        game.add(surrender);
        MenuItem exit = new MenuItem("Exit");
        game.add(exit);
        game.addSeparator();
        
        surrender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(frame,
                                "Ban co chac la muon dau hang khong ?", "Thong bao",
                                JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        oos.writeObject("checkwin,123");
                    } catch (IOException ex) {
                    }
                    Object[] options = { "Xem lai", "Thoat" };
                                int m = JOptionPane.showConfirmDialog(frame,
                                                "Ban da thua. Ban co muon xem lai khong ?", "Thong bao",
                                                JOptionPane.YES_NO_OPTION);
                                if (m == JOptionPane.YES_OPTION) {
                                    thoigian.stop();
                                } else if (m == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                } 
            }
        });
        
        exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        oos.writeObject("checkwin,123");
                    } catch (IOException ex) {
                    }
                    System.exit(0);
                }
        });
        
        about.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"OK"};
                    JOptionPane.showConfirmDialog(frame,
                                "Thiết kế và hoàn thiện bởi: Huỳnh Vĩnh Phúc\n Mã số sinh viên: B2012129", "Information",
                                JOptionPane.CLOSED_OPTION); 
                }
        });
        
        help.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"OK"};
                    JOptionPane.showConfirmDialog(frame,
                                "Luật chơi rất đơn giản bạn chỉ cần ăn được vua đối phương là bạn thắng\n"
                                        + "Mỗi lượt có 90 giây suy nghĩ\n"
                                        + "Pawn đi thẳng, ăn chéo. Lượt đầu tiên có thể đi 2 ô, các lượt sau đó đi 1 ô\n"
                                        + "Rook đi thẳng và ngang. Không giới hạn ô có thể đi, không thể đi xuyên qua các quân cờ khác\n"
                                        + "Knight đi chữ L. Có thể đi xuyên qua các quân cờ khác\n"
                                        + "Bishop đi chéo. Không giới hạn ô có thể đi, không thể đi xuyên qua các quân cờ khác\n"
                                        + "Queen đi thẳng, ngang và chéo. Không giới hạn ô có thể đi, không thể đi xuyên qua các quân cờ khác\n"
                                        + "King đi thẳng, ngang và chéo. Chỉ có thể đi 1 ô", "Luật Chơi",
                                JOptionPane.CLOSED_OPTION); 
                }
        });
        
        demthoigian = new JLabel("Thời gian còn lại: ");
        demthoigian.setFont(new Font("TimesRoman", Font.ITALIC, 16));
        demthoigian.setForeground(Color.BLACK);
        frame.add(demthoigian);
        demthoigian.setBounds(532, 30, 300, 50);
        second = 90;
        
        thoigian = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String temp = second.toString();
                    if (temp.length() == 1) {
                        temp = "0" + temp;
                    }
                    if (second == 0) {
                            try {
                                oos.writeObject("checkwin,123");
                            } catch (IOException ex) {
                            }
                            Object[] options = { "Xem lai", "Thoat" };
                            int m = JOptionPane.showConfirmDialog(frame,
                                            "Ban da thua. Ban co muon xem lai khong ?", "Thong bao",
                                            JOptionPane.YES_NO_OPTION);
                            if (m == JOptionPane.YES_OPTION) {
                                thoigian.stop();
                            } else if (m == JOptionPane.NO_OPTION) {
                                System.exit(0);
                            }
                    } else {
                        demthoigian.setText("Thời gian còn lại: " + temp + " giây" );
                        second--;
                    }
            }
        });
        
        Font fo = new Font("Arial",Font.BOLD,15);
        content = new JTextArea();
        content.setFont(fo);
        content.setBackground(Color.white);
        
        content.setEditable(false);
        JScrollPane sp = new JScrollPane(content);
        sp.setBounds(532,80,310,300);
        send = new JButton("Gui");
        send.setBounds(771, 390, 70, 30);
        enterchat = new JTextField("");
        enterchat.setFont(fo);
        enterchat.setBounds(532, 390, 230, 30);
        enterchat.setBackground(Color.white);
        frame.add(enterchat);
        frame.add(send);
        frame.add(sp);
        frame.setVisible(true);
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(send)){
                    try{
                        temp+="Tôi: " + enterchat.getText() + "\n";
                        content.setText(temp);
                        oos.writeObject("chat," + enterchat.getText());
                        enterchat.setText("");
                        enterchat.requestFocus();
                        content.setVisible(false);
                        content.setVisible(true);
                    }catch (Exception r){
                        r.printStackTrace();
                    }
                }
            }
        });
        
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for(int y = 0; y < 8; y++){
                    for(int x = 0; x < 8; x++){
                         if (white){
                             g.setColor(new Color(255,248,220));
                         }else{
                             g.setColor(new Color(184,134,11));
                         }
                         g.fillRect(x*64, y*64, 64, 64);
                         white = !white;
                    }
                    white = !white;
                }
                
                for(Piece p : ps){
                    int ind = 0;
                    if(p.name.equalsIgnoreCase("King")){
                        ind = 0;
                    }
                    if(p.name.equalsIgnoreCase("Queen")){
                        ind = 1;
                    }
                    if(p.name.equalsIgnoreCase("Bishop")){
                        ind = 2;
                    }
                    if(p.name.equalsIgnoreCase("Knight")){
                        ind = 3;
                    }
                    if(p.name.equalsIgnoreCase("Rook")){
                        ind = 4;
                    }  
                    if(p.name.equalsIgnoreCase("Pawn")){
                        ind = 5;
                    }
                    if(p.name.equalsIgnoreCase("Non")){
                        ind = 12;
                    }
                    if(p.isWhite != 1 && p.isWhite !=2){
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
    };
        
        pn.setBounds(10,10, 512, 512);
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece.isWhite != 2){
                   selectedPiece.x = e.getX()-46;
                   selectedPiece.y = e.getY()-92;
                   frame.repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
             }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
             }
            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX()-16, e.getY()-60);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(selectedPiece.isWhite != 2){
                    if(turnplay == 1){
                        if(selectedPiece.isWhite == 1){
                            if(!((e.getX()-16)/64 == selectedPiece.xp && (e.getY()-60)/64 == selectedPiece.yp) && ((e.getX()-16)/64  < 8) && ((e.getX()-16)/64 > -1) && ((e.getY()-60)/64 < 8) && ((e.getY()-60)/64 > -1)){
                                int f = selectedPiece.turn;
                                int a = selectedPiece.xp;
                                int b = selectedPiece.yp;
                                selectedPiece.moveServer((e.getX()-16)/64, (e.getY()-60)/64, selectedPiece.xp, selectedPiece.yp);
                                frame.repaint();
                                if(selectedPiece.checkKing() == 1){
                                    try {
                                        oos.writeObject("checkenemywin,123");
                                    } catch (IOException ex) {}
                                    int m = JOptionPane.showConfirmDialog(frame,
                                                "Ban da thang. Ban co muon xem lai khong ?", "Thong bao",
                                                JOptionPane.YES_NO_OPTION);
                                    if (m == JOptionPane.YES_OPTION) {
                                        thoigian.stop();
                                    } else if (m == JOptionPane.NO_OPTION) {
                                        System.exit(0);
                                    }
                                }

                                if (f != selectedPiece.turn){
                                    try {
                                        oos.writeObject("move," + (e.getX()-16)/64 + "," + (e.getY()-60)/64 + "," + a + "," + b);
                                    } catch (IOException ie) {
                                    }
                                    thoigian.stop();
                                    if(selectedPiece.name.equalsIgnoreCase("Pawn") && ((e.getY()-60)/64 == 0)){
                                        ExQueen[NumExwQueen].upQueenServer((e.getX()-16)/64, (e.getY()-60)/64);
                                        frame.repaint();
                                        NumExwQueen++;
                                    }
                                    turnplay = 0;
                                    try {
                                        oos.writeObject("changeturns,123");
                                    } catch (IOException ex) {
                                    }
                                }
                            }else{
                                selectedPiece.notmove();
                                frame.repaint();
                            } 
                        }else{
                            System.out.println("Not your team\nYour team is White");
                            selectedPiece.notmove();
                            frame.repaint();
                        }
                    }else{
                        System.out.println("Not your turn");
                        selectedPiece.notmove();
                        frame.repaint();
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        
        try {
            serversocket = new ServerSocket(1234);
            System.out.println("Dang doi client...");
            socket = serversocket.accept();
            System.out.println("Client da ket noi!");
            os = socket.getOutputStream();
            is = socket.getInputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(is);
            while (true) {
                String stream = ois.readObject().toString();
                String[] data = stream.split(",");
                if (data[0].equals("chat")) {
                        temp += "Khách:" + data[1] + '\n';
                        content.setText(temp);
                } else if (data[0].equals("move")) {
                        thoigian.start();
                        second = 90;
                        int data1 = Integer.parseInt(data[1]);
                        int data2 = Integer.parseInt(data[2]);
                        int data3 = Integer.parseInt(data[3]);
                        int data4 = Integer.parseInt(data[4]);
                        selectedPiece1 = getPiece(data3*64, data4*64);
                        selectedPiece1.moveServer(data1, data2, data3, data4);
                        frame.repaint();
                        if(selectedPiece1.name.equalsIgnoreCase("Pawn") && data2 == 7){
                            ExQueen[NumExbQueen].upQueenServer(data1, data2);
                            NumExbQueen++;
                            frame.repaint();
                        }
                }else if(data[0].equals("changeturns")){
                    turnplay = 1;
                }else if (data[0].equals("checkwin")) {
                    thoigian.stop(); 
                    int m = JOptionPane.showConfirmDialog(frame,
                                        "Ban da thang. Ban co muon xem lai khong ?", "Thong bao",
                                        JOptionPane.YES_NO_OPTION);
                    if (m == JOptionPane.YES_OPTION) {
                        thoigian.stop();
                    } else if (m == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }else if (data[0].equals("checkenemywin")) {
                    thoigian.stop(); 
                    int m = JOptionPane.showConfirmDialog(frame,
                                        "Ban da thua. Ban co muon xem lai khong ?", "Thong bao",
                                        JOptionPane.YES_NO_OPTION);
                    if (m == JOptionPane.YES_OPTION) {
                        thoigian.stop();
                    } else if (m == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }
            }
        } catch (Exception ie) {
        }
    }
   
    public static Piece getPiece(int x, int y) {
        int xp = x/64;
        int yp = y/64;
        Piece n = new Piece(8, 8, 2, "Non", ps,0);
        for(Piece p : ps){
            if(p.xp == xp && p.yp == yp && p != n){
                return p;
            }
        }
        return n;
    }
    
    public static void main(String[] args) throws IOException {
        new ChessServer();
    }
    
}
