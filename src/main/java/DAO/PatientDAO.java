package DAO;

import com.google.common.collect.Lists;
import entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@SuppressWarnings("all")
public class PatientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete(String id) {
        StringBuilder sql = new StringBuilder("delete from patient where id = ?");
        List<Object> param = Lists.newArrayList();
        param.add(id);
        jdbcTemplate.update(sql.toString(), param.toArray());
    }

    public List<Patient> query(Patient patient) {
        if (patient == null)
            return Lists.newArrayList();
        StringBuilder sql = new StringBuilder("select * from patient where 1 = 1 ");
        List<Object> param = Lists.newArrayList();
        if (Objects.nonNull(patient.getUid())) {
            sql.append(" and uid = ?");
            param.add(patient.getUid());
        }
        if (Objects.nonNull(patient.getPatientName())) {
            sql.append(" and patient_name = ?");
            param.add(patient.getPatientName());
        }
        sql.append(" order by id desc");
        List<Patient> result = jdbcTemplate.query(sql.toString(), param.toArray(), new BeanPropertyRowMapper<Patient>(Patient.class));
        return result;
    }

    public void update(Patient patient) {
        if (patient == null || patient.getId() == null)
            return;
        List<Object> param = Lists.newArrayList();
        param.add(patient.getUid());
        param.add(patient.getPatientName());
        param.add(patient.getSex());
        param.add(patient.getBirthYear());
        param.add(patient.getNativePlace());
        param.add(patient.getAddress());
        param.add(patient.getPhone());
        param.add(patient.getId());
        jdbcTemplate.update("update patient set uid=?, patientName = ?, sex=? , birthYear=? , nativePlace=?, address =?, phone=? where id =?", param.toArray());
    }

    public void add(Patient patient) {
        if (patient == null)
            return;
        StringBuilder sql = new StringBuilder("insert into patient(uid,patient_name,sex,birth_year,native_place,address,phone) values (?,?,?,?,?,?,?)");
        List<Object> param = Lists.newArrayList();
        param.add(patient.getUid());
        param.add(patient.getPatientName());
        param.add(patient.getSex());
        param.add(patient.getBirthYear());
        param.add(patient.getNativePlace());
        param.add(patient.getAddress());
        param.add(patient.getPhone());
        jdbcTemplate.update(sql.toString(), param.toArray());
    }
}
