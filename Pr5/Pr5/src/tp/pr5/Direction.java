package tp.pr5;
/**
 * Es una clase enumerado que contiene todas las direcciones posibles(north, south, east, west) m�s un valor que representa la direcci�n desconocida(unknown).
 * Se encarga de dada una direcci�n, devolver la direcci�n opuesta y tambi�n de dada una rotaci�n(derecha o izuierda) devolver la direcci�n correspondiente.
 * @author Nerea Ram�rez y Carmen Acosta
 * 
 *
 */
public enum Direction {
	EAST, NORTH, WEST, SOUTH, UNKNOWN;
	/**
	 * Se encarga de dada una direcci�n devolver la opuesta comparando con todas las direcciones posibles.
	 * @param dir
	 * @return Se devuelve la direcci�n hallada. Si la direcci�n dada no se encuentra en el enumerado se devolver� unknown.
	 */
	public Direction dirOpuesta (Direction dir){
		Direction dirOpuesta = Direction.UNKNOWN;
		
		if(dir == Direction.NORTH ){
			dirOpuesta=Direction.SOUTH;
			
		}else if(dir==Direction.SOUTH){
			dirOpuesta=Direction.NORTH;
			
		}else if(dir== Direction.EAST){
			dirOpuesta= Direction.WEST;
		
		}else if(dir== Direction.WEST){
			dirOpuesta= Direction.EAST;
		
		}else
		{
			dirOpuesta=Direction.UNKNOWN;
		}
		
		return dirOpuesta;
	}
	/**
	 * Actualiza la dirección actual según la rotación introducida como parámetro.
	 * @param rotation
	 * @return devuelve la dirección actualizada.
	 */
	public Direction rotate (Rotation rotation){
		Direction direction = UNKNOWN;
		if ( rotation == Rotation.LEFT) {
			if ( this == Direction.EAST){
				direction = NORTH;
			}
			else if ( this == Direction.NORTH ){
				direction = WEST;
			}
			else if ( this == Direction.WEST){
				direction = SOUTH;
			}
			else if ( this == Direction.SOUTH){
				direction = EAST;
			}
		}else if (rotation == Rotation.RIGHT){
			if ( this == Direction.EAST){
				direction = SOUTH;
			}
			else if ( this == Direction.NORTH ){
				direction = EAST;
			}
			else if ( this == Direction.WEST){
				direction = NORTH;
			}
			else if ( this == Direction.SOUTH){
				direction = WEST;
			}
			else {
				direction = UNKNOWN;
			}
		}
		return direction;
	}
}
