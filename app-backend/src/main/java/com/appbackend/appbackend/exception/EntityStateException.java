 /*
  * Reference Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
  *
  * This code is intended for educational purposes only.
  */
 package com.appbackend.appbackend.exception;

 /**
  * A checked exception indicating problem related to existence of an entity.
  */
 public class EntityStateException extends IllegalArgumentException {
     public EntityStateException(String s) {
         super(s);
     }
 }

