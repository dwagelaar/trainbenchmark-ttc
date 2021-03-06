package hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.util.InverseRouteDefinitionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate RouteSensorMatcher in a type-safe way.
 * 
 * @see RouteSensorMatcher
 * @see RouteSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class RouteSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<RouteSensorMatcher> {
  private RouteSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static RouteSensorQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected RouteSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return RouteSensorMatcher.on(engine);
  }
  
  @Override
  public RouteSensorMatch newEmptyMatch() {
    return RouteSensorMatch.newEmptyMatch();
  }
  
  @Override
  public RouteSensorMatch newMatch(final Object... parameters) {
    return RouteSensorMatch.newMatch((hu.bme.mit.trainbenchmark.ttc.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.ttc.railway.Sensor) parameters[1], (hu.bme.mit.trainbenchmark.ttc.railway.SwitchPosition) parameters[2], (hu.bme.mit.trainbenchmark.ttc.railway.Switch) parameters[3]);
  }
  
  private static class LazyHolder {
    private final static RouteSensorQuerySpecification INSTANCE = make();
    
    public static RouteSensorQuerySpecification make() {
      return new RouteSensorQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static RouteSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.ttc.benchmark.emfincquery.routeSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","sensor","switchPosition","sw");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("route", "hu.bme.mit.trainbenchmark.ttc.railway.Route"),new PParameter("sensor", "hu.bme.mit.trainbenchmark.ttc.railway.Sensor"),new PParameter("switchPosition", "hu.bme.mit.trainbenchmark.ttc.railway.SwitchPosition"),new PParameter("sw", "hu.bme.mit.trainbenchmark.ttc.railway.Switch"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_route = body.getOrCreateVariableByName("route");
      	PVariable var_sensor = body.getOrCreateVariableByName("sensor");
      	PVariable var_switchPosition = body.getOrCreateVariableByName("switchPosition");
      	PVariable var_sw = body.getOrCreateVariableByName("sw");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_route, "route"),
      				
      		new ExportedParameter(body, var_sensor, "sensor"),
      				
      		new ExportedParameter(body, var_switchPosition, "switchPosition"),
      				
      		new ExportedParameter(body, var_sw, "sw")
      	));
      	new TypeBinary(body, CONTEXT, var_route, var_switchPosition, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark", "Route", "follows"), "http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark/Route.follows");
      	new TypeBinary(body, CONTEXT, var_switchPosition, var_sw, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark", "SwitchPosition", "switch"), "http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark/SwitchPosition.switch");
      	new TypeBinary(body, CONTEXT, var_sw, var_sensor, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark", "TrackElement", "sensor"), "http://www.semanticweb.org/ontologies/2015/ttc/trainbenchmark/TrackElement.sensor");
      	new NegativePatternCall(body, new FlatTuple(var_sensor, var_route), InverseRouteDefinitionQuerySpecification.instance().getInternalQueryRepresentation());
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
