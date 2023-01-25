package org.scrinch.model;

import java.util.Stack;
import java.util.logging.Level;

public class UndoManager {
    
    private class UndoableAndState{
        public UndoableAndState(Undoable undoableObject, Object state){
            this.undoableObject = undoableObject;
            this.state = state;
        }
        public Undoable undoableObject;
        public Object state;
    }
    private Stack<UndoableAndState> undoEvents = new Stack<UndoableAndState>();
    private boolean started;
    
    private static UndoManager instance;
    
    public static UndoManager getInstance(){
        if(instance==null){
            instance = new UndoManager();
        }
        return instance;
    }
    
    private UndoManager(){
    }
    
    public void start(){
        this.started = true;
    }
    
    public void storeUndoableState(Undoable undoableObject, Object state){
        if(this.started){
            undoEvents.push(new UndoableAndState(undoableObject, state));
        }
    }
    
    public synchronized void undoLastEvent(){
        if(!undoEvents.empty()){
            UndoableAndState undoableAndState = undoEvents.pop();
            try{
                undoableAndState.undoableObject.restore(undoableAndState.state);
            }catch(Exception e){
                ScrinchEnvToolkit.logger.log(
                        Level.WARNING, 
                        "Could not restore state for object : "
                            +undoableAndState.undoableObject
                            +"\n", e);
            }
        }
    }
}
