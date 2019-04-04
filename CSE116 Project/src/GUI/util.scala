package GUI

class util (var x:Double, var y:Double) {
  //Default constructor creates a util of zeroes
  def this() = this(0,0)

  //Copy constructor create a "deep" copy of the input object
  def this(toCopy:util) = this(toCopy.x, toCopy.y)

  //Provided toString method simplifies printing, e.g. println(vec.toString) OR println(vec)
  override def toString():String = "("+x+","+y+")"

  //Methods for addition and subtraction of vectors
  def + (other:util):util = { new util(x + other.x, y + other.y) }
  def - (other:util):util = { new util(x - other.x, y - other.y) }

  def += (other:util) { this.x += other.x; this.y += other.y }
  def -= (other:util) { this.x -= other.x; this.y -= other.y }

  //Methods for multiplication and division of vectors by a scalar (non-vector)
  def * (scalar:Double):util = { new util(x * scalar, y * scalar) }
  def / (scalar:Double):util = { new util(x / scalar, y / scalar) }

  def *= (scalar:Double) { this.x *= scalar; this.y *= scalar }
  def /= (scalar:Double) { this.x /= scalar; this.y /= scalar }

  //Methods to determine the length of a vector (magnitude and length should return the same value)
  def magnitude():Double = { math.sqrt(x*x + y*y) }
  def length():Double = { magnitude }

  //Method to return a new vector that is in the same direction, but length 1
  def normalize():util = { this / magnitude }
  def unary_~() { this /= magnitude }

  //Methods to calculate the dot product, and determine the angle between 2 vectors
  def dot(other:util):Double = { x * other.x + y * other.y }
  def **(other:util):Double = this.dot(other)

  def angleBetween(other:util):Double = { math.acos(dot(other) / (magnitude * other.magnitude)) }
  def <>(other:util):Double = this.angleBetween(other)
}