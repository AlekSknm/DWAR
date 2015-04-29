package com.miage.fralml;
 
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
 
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Info {
        @PrimaryKey
        @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
        Long id;
        @Persistent
        String name;
        @Persistent
        String unable;
 
        public Long getId() {
                return id;
        }
 
        public void setId(Long id) {
                this.id = id;
        }
 
        public String getName() {
                return name;
        }
 
        public void setName(String name) {
                this.name = name;
        }
 
        public String getUnable() {
                return unable;
        }
 
        public void setUnable(String unable) {
                this.unable = unable;
        }
 
}