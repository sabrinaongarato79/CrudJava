
package ar.charlycimino.cac.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charly Cimino
 * Aprendé más Java en mi canal: https://www.youtube.com/c/CharlyCimino
 * Encontrá más código en mi repo de GitHub: https://github.com/CharlyCimino
 */
public class ModeloMySQL implements Modelo {
    
    private static final String GET_ALL_QUERY = "SELECT * FROM alumnos";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM alumnos WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM alumnos WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE alumnos SET nombre=?, apellido=?, mail=?, fechaNac=?, fotobase64=? WHERE id=?";
    private static final String ADD_QUERY = "INSERT INTO alumnos VALUES(null, ?, ?, ?, ?, ?)";
    
    @Override
    public List<Alumno> getAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);
            ResultSet rs = ps.executeQuery();) {            
            while(rs.next()) {
                lista.add( rsToAlumno(rs) );
            }         
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener lista de alumnos", ex);
        }       
        return lista;
    }

    @Override
    public Alumno getAlumno(int id) {
        Alumno alumno = null;
        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);
            ) {            
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                rs.next();
                alumno = rsToAlumno(rs);
            }    
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener alumno por ID", ex);
        }
        
        return alumno;
    }

    @Override
    public int addAlumno(Alumno alumno) {
        int regsAgregados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(ADD_QUERY);
                ) {
            fillPreparedStatement(ps, alumno);
            System.out.print("Alumno jeje: " + alumno);
            regsAgregados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar alumno", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateAlumno(Alumno alumno) {
        int regsActualizados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
                ) {
            fillPreparedStatement(ps, alumno);
            ps.setInt(6, alumno.getId());
            regsActualizados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar alumno", ex);
        }
        return regsActualizados;
    }

    @Override
    public int removeAlumno(int id) {
        int regsBorrados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
                ) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar alumno", ex);
        }
        return regsBorrados;
    }
    
    private void fillPreparedStatement(PreparedStatement ps, Alumno alu) throws SQLException {
        ps.setString(1, alu.getNombre());
        ps.setString(2, alu.getApellido());
        ps.setString(3, alu.getMail());
        ps.setString(4, alu.getFechaNacimiento());
        ps.setString(5, alu.getFoto());        
    }
    
    private Alumno rsToAlumno(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String mail = rs.getString("mail");
        String fechaNac = rs.getString("fechaNac");
        String fotoBase64 = rs.getString("fotoBase64");
        return new Alumno(id, nombre, apellido, mail, fechaNac, fotoBase64);
    }
    
}
