static class Vertex{
	public int name;
	public int distance;

	public Vertex(int name, int distance){
		this.name = name;
		this.distance = distance;
	}

	public String toString(){
		return "Name: "+name+", Weight: "+distance;
	}
}
