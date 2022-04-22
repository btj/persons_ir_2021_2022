package persons_ir;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * Each instance of this class represents a person in an ... is father of ... graph.
 * 
 * @invar | getFather() == null || getFather().getChildren().contains(this)
 * @invar | getChildren().stream().allMatch(c -> c.getFather() == this)
 */
public class Person {
	
	/**
	 * @invar | children != null
	 * @invar | children.stream().allMatch(c -> c != null)
	 * @invar | children.stream().allMatch(c -> c.father == this)
	 * @invar | father == null || father.children.contains(this)
	 * @peerObject
	 */
	private Person father;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	private HashSet<Person> children;

	/**
	 * @peerObject
	 */
	public Person getFather() { return father; }
	
	/**
	 * @post | result != null
	 * @post | result.stream().allMatch(c -> c != null)
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Person> getChildren() { return Set.copyOf(children); }
	
	/**
	 * @post | getFather() == null
	 * @post | getChildren().size() == 0
	 */
	public Person() {
		children = new HashSet<>();
	}
	
	/**
	 * @pre | getFather() == null
	 * @pre | father != null
	 * @mutates_properties | this.getFather(), father.getChildren()
	 * @post | getFather() == father
	 * @post | father.getChildren().equals(LogicalSet.plus(old(father.getChildren()), this))
	 */
	public void setFather(Person father) { 
		this.father = father;
		father.children.add(this);
	}
	
	/**
	 * @pre | getFather() != null
	 * @mutates_properties | getFather(), getFather().getChildren()
	 * @post | getFather() == null
	 * @post | old(getFather()).getChildren().equals(LogicalSet.minus(old(getFather().getChildren()), this))
	 */
	public void unregisterFather() {
		father.children.remove(this);
		this.father = null;
	}
	
}
