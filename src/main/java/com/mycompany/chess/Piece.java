/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chess;

import static java.lang.Math.abs;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    int isWhite;
    int turn;
    LinkedList<Piece> ps;
    String name;
    public Piece(int xp, int yp, int isWhite, String n, LinkedList<Piece> ps, int turn) {
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);
    }
    
    public void moveServer(int xp, int yp, int cxp, int cyp){
 
        if(name.equalsIgnoreCase("Pawn") && isWhite == 1){   //Pawn white
            if((xp == cxp) 
                && (yp == cyp - 1 || yp == cyp - 2 ) 
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 2)
                && turn == 0    ){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp) 
                && (yp == cyp - 1) 
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 2)){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp + 1  || xp == cxp -1)
                && (yp == cyp - 1 )
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 0)){
                    ChessServer.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
            }
        }
        
        
        if(name.equalsIgnoreCase("Pawn") && isWhite == 0){   //Pawn Black
            if((xp == cxp) 
                && (yp == cyp + 1 || yp == cyp + 2 ) 
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 2)
                && turn == 0    ){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp) 
                && (yp == cyp + 1) 
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 2)){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp + 1  || xp == cxp -1)
                && (yp == cyp + 1 )
                && (ChessServer.getPiece(xp*64, yp*64).isWhite == 1)){
                    ChessServer.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
            }
        }  
        
        
        if(name.equalsIgnoreCase("Rook")){
            int canduong = 0;
            if(xp == cxp && yp != cyp){
                if(yp > cyp){
                    for(int i = cyp+1; i< yp; i++){
                        if(!ChessServer.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = yp+1; i< cyp; i++){
                        if(!ChessServer.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessServer.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }else{
                if(xp != cxp && yp == cyp){
                    if(xp > cxp){
                    for(int i = cxp+1; i< xp; i++){
                        if(!ChessServer.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = xp+1; i< cxp; i++){
                        if(!ChessServer.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessServer.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
        }
        
        
        if(name.equalsIgnoreCase("Bishop")){
            if(abs(xp-cxp) ==  abs(yp-cyp) ){
                int canduong = 0, hsgtx = 1, hsgty = 1;
                if(xp - cxp < 0) hsgtx = -1;
                if(yp - cyp < 0) hsgty = -1;
                for(int i = 1; i < abs(xp - cxp); i++){
                    if(!ChessServer.getPiece((cxp + hsgtx*i)*64, (cyp + hsgty*i)*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                    }
                }
                if(canduong == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessServer.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                } 
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;
            }
        }
        
        
        if(name.equalsIgnoreCase("Queen")){
            int test = 0;
            int canduong = 0;
            if(xp == cxp && yp != cyp){
                if(yp > cyp){
                    for(int i = cyp+1; i< yp; i++){
                        if(!ChessServer.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = yp+1; i< cyp; i++){
                        if(!ChessServer.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
            }else{
                if(xp != cxp && yp == cyp){
                    if(xp > cxp){
                    for(int i = cxp+1; i< xp; i++){
                        if(!ChessServer.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = xp+1; i< cxp; i++){
                        if(!ChessServer.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
                }
            }
            if(abs(xp-cxp) ==  abs(yp-cyp) ){
                int canduong1 = 0, hsgtx = 1, hsgty = 1;
                if(xp - cxp < 0) hsgtx = -1;
                if(yp - cyp < 0) hsgty = -1;
                for(int i = 1; i < abs(xp - cxp); i++){
                    if(!ChessServer.getPiece((cxp + hsgtx*i)*64, (cyp + hsgty*i)*64).name.equalsIgnoreCase("Non")){
                            canduong1 = 1;
                    }
                }
                if(canduong1 == 0 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
            }
            if(test == 1){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;
            }
        }
        
        
        if(name.equalsIgnoreCase("Knight")){
            int testp = 0;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 2) testp = 1;
            if(abs(xp - cxp) == 2 && abs(yp - cyp) == 1) testp = 1;
            if(testp == 1 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;  
            }
        }
        
        
        if(name.equalsIgnoreCase("King")){
            int testp = 0;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 1) testp = 1;
            if(abs(xp - cxp) == 0 && abs(yp - cyp) == 1) testp = 1;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 0) testp = 1;
            if(testp == 1 && ChessServer.getPiece(xp*64, yp*64).isWhite != isWhite){
                ChessServer.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;  
            }
        }
        
        
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
    }
    
    public void moveClient(int xp, int yp, int cxp, int cyp){
        
        if(name.equalsIgnoreCase("Pawn") && isWhite == 1){   //Pawn white
            if((xp == cxp) 
                && (yp == cyp - 1 || yp == cyp - 2 ) 
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 2)
                && turn == 0    ){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp) 
                && (yp == cyp - 1) 
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 2)){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp + 1  || xp == cxp -1)
                && (yp == cyp - 1 )
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 0)){
                    ChessClient.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
            }
        }
        
        
        if(name.equalsIgnoreCase("Pawn") && isWhite == 0){   //Pawn Black
            if((xp == cxp) 
                && (yp == cyp + 1 || yp == cyp + 2 ) 
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 2)
                && turn == 0    ){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp) 
                && (yp == cyp + 1) 
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 2)){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                if((xp == cxp + 1  || xp == cxp -1)
                && (yp == cyp + 1 )
                && (ChessClient.getPiece(xp*64, yp*64).isWhite == 1)){
                    ChessClient.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
            }
        }  
        
        
        
        if(name.equalsIgnoreCase("Rook")){
            int canduong = 0;
            if(xp == cxp && yp != cyp){
                if(yp > cyp){
                    for(int i = cyp+1; i< yp; i++){
                        if(!ChessClient.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = yp+1; i< cyp; i++){
                        if(!ChessClient.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessClient.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }else{
                if(xp != cxp && yp == cyp){
                    if(xp > cxp){
                    for(int i = cxp+1; i< xp; i++){
                        if(!ChessClient.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = xp+1; i< cxp; i++){
                        if(!ChessClient.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessClient.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                }
            }
        }
        
        
        if(name.equalsIgnoreCase("Bishop")){
            if(abs(xp-cxp) ==  abs(yp-cyp) ){
                int canduong = 0, hsgtx = 1, hsgty = 1;
                if(xp - cxp < 0) hsgtx = -1;
                if(yp - cyp < 0) hsgty = -1;
                for(int i = 1; i < abs(xp - cxp); i++){
                    if(!ChessClient.getPiece((cxp + hsgtx*i)*64, (cyp + hsgty*i)*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                    }
                }
                if(canduong == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    ChessClient.getPiece(xp*64, yp*64).kill();
                    turn++;
                }else{
                    x = this.xp*64;
                    y = this.yp*64;
                    return;
                } 
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;
            }
        }
        
        
        if(name.equalsIgnoreCase("Queen")){
            int test = 0;
            int canduong = 0;
            if(xp == cxp && yp != cyp){
                if(yp > cyp){
                    for(int i = cyp+1; i< yp; i++){
                        if(!ChessClient.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = yp+1; i< cyp; i++){
                        if(!ChessClient.getPiece(xp*64, i*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
            }else{
                if(xp != cxp && yp == cyp){
                    if(xp > cxp){
                    for(int i = cxp+1; i< xp; i++){
                        if(!ChessClient.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }else{
                    for(int i = xp+1; i< cxp; i++){
                        if(!ChessClient.getPiece(i*64, yp*64).name.equalsIgnoreCase("Non")){
                            canduong = 1;
                        }
                    } 
                }
                if(canduong == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
                }
            }
            if(abs(xp-cxp) ==  abs(yp-cyp) ){
                int canduong1 = 0, hsgtx = 1, hsgty = 1;
                if(xp - cxp < 0) hsgtx = -1;
                if(yp - cyp < 0) hsgty = -1;
                for(int i = 1; i < abs(xp - cxp); i++){
                    if(!ChessClient.getPiece((cxp + hsgtx*i)*64, (cyp + hsgty*i)*64).name.equalsIgnoreCase("Non")){
                            canduong1 = 1;
                    }
                }
                if(canduong1 == 0 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                    test = 1;
                }
            }
            if(test == 1){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;
            }
        }
        
        
        if(name.equalsIgnoreCase("Knight")){
            int testp = 0;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 2) testp = 1;
            if(abs(xp - cxp) == 2 && abs(yp - cyp) == 1) testp = 1;
            if(testp == 1 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;  
            }
        }
        
        
        if(name.equalsIgnoreCase("King")){
            int testp = 0;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 1) testp = 1;
            if(abs(xp - cxp) == 0 && abs(yp - cyp) == 1) testp = 1;
            if(abs(xp - cxp) == 1 && abs(yp - cyp) == 0) testp = 1;
            if(testp == 1 && ChessClient.getPiece(xp*64, yp*64).isWhite != isWhite){
                ChessClient.getPiece(xp*64, yp*64).kill();
                turn++;
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;  
            }
        }
       
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
    }
    
    public void notmove(){
        x = this.xp*64;
        y = this.yp*64;
        return;  
    }
    
    public void upQueenServer(int xp, int yp){
        ChessServer.getPiece(xp*64, yp*64).kill();
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
    }
    
    public void upQueenClient(int xp, int yp){
        ChessClient.getPiece(xp*64, yp*64).kill();
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
    }
    
    public int checkKing() {
        for(Piece p : ps){
            if((p.name.equalsIgnoreCase("King") && p.isWhite != isWhite)){
                return 0;
            }
        }
        return 1;
    }
    
    public void kill() {
        ps.remove(this);
    }
}
