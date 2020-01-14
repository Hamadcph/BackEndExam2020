/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hamad
 */
//Genereret fra https://codebeautify.org/json-to-java-converter
public class metaRating {
 private String title;
 private String source;
 private float metacritic;


 // Getter Methods 

 public String getTitle() {
  return title;
 }

 public String getSource() {
  return source;
 }

 public float getMetacritic() {
  return metacritic;
 }

 // Setter Methods 

 public void setTitle(String title) {
  this.title = title;
 }

 public void setSource(String source) {
  this.source = source;
 }

 public void setMetacritic(float metacritic) {
  this.metacritic = metacritic;
 }
 
}
