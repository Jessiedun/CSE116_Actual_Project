package Games

class map {
  var startingLocation = new gridLocation(0, 3)
  var gridx: Int = 25
  var gridy: Int = 9
  def niceMap(newMap: Int): gridLocation = {
      startingLocation = new gridLocation(0 , 3)
      startingLocation
    }
  }